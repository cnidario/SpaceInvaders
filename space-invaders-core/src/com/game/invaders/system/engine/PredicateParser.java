package com.game.invaders.system.engine;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.game.invaders.scene.actor.ActorComponent;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;

public class PredicateParser {
	private enum OPS {
		PAREN, AND, OR, NOT
	}
	private static String readNext(String input, int start) {
		Matcher m = Pattern.compile("\\s*(\\(|\\)|&|\\||!|[a-zA-Z_]+)\\s*").matcher(input);
		if(m.find(start))
			return m.group();
		return null;
	}
	private static void opToOutputStack(OPS op, Stack<ComponentPredicate> outputStack) {
		if(op == OPS.NOT && outputStack.size() > 0) {
			ComponentPredicate p = outputStack.pop();
			outputStack.push(new NotPredicate(p));
		} else if(outputStack.size() > 1) {
			ComponentPredicate a = outputStack.pop();
			ComponentPredicate b = outputStack.pop();
			if(op == OPS.AND)
				outputStack.push(new AndPredicate(a, b));
			else if(op == OPS.OR)
				outputStack.push(new OrPredicate(a, b));
			else
				throw new IllegalArgumentException("output stack unknown operand ?");
		} else
			throw new IllegalArgumentException("output stack not enough operands for " + op);
	}
	public static ComponentPredicate parse(String input) {
		//Implementación del Shunting-yard algorithm
		Stack<ComponentPredicate> outputStack = new Stack<ComponentPredicate>(); 
		Stack<OPS> operatorStack = new Stack<PredicateParser.OPS>();
		int index = 0;
		
		while(index < input.length()) {
			String symbol = readNext(input, index);
			int nextIndex = symbol.length();
			symbol = symbol.trim();
			if(symbol.matches("[a-zA-Z_]+")) {
				Class<?> clazz;
				try {
					clazz = Class.forName("com.game.invaders.scene.actor.components." + symbol);
				} catch (ClassNotFoundException e) {
					throw new IllegalArgumentException("Component name not found: " + symbol);
				}
				ActorComponentID cid;
				try {
					cid = ((ActorComponent)clazz.newInstance()).getID();
				} catch (Exception e) {
					throw new IllegalArgumentException("Component cannot be instantiated: " + symbol);
				}
				outputStack.push(new MatchPredicate(cid));
			} else if(symbol.equals("!")) {
				operatorStack.push(OPS.NOT);
			} else if(symbol.equals("&") || symbol.equals("&")) {
				OPS current = symbol.equals("&")? OPS.AND : OPS.OR; 
				OPS top;
				while(!operatorStack.isEmpty() && 
						((top = operatorStack.peek()).ordinal() > current.ordinal()) &&
						top != OPS.PAREN) {
					top = operatorStack.pop();
					opToOutputStack(top, outputStack);
					operatorStack.push(current);
				}
			} else if(symbol.equals("(")) {
				operatorStack.push(OPS.PAREN);
			} else if(symbol.equals(")")) {
				while(!operatorStack.isEmpty() && operatorStack.peek() != OPS.PAREN) {
					opToOutputStack(operatorStack.pop(), outputStack);
				}
				if(!operatorStack.isEmpty() && operatorStack.pop() == OPS.PAREN)
					;
				else
					throw new IllegalArgumentException("Unmatched closing parenthesis");
			}
			index += nextIndex;
		}
		while(!operatorStack.isEmpty()) {
			OPS top = operatorStack.pop();
			if(top == OPS.PAREN)
				throw new IllegalArgumentException("Unmatched opening parenthesis");
			else
				opToOutputStack(top, outputStack);
		}
		if(outputStack.size() == 1)
			return outputStack.pop();
		else
			throw new IllegalArgumentException("Unknown error, outputStack at end > 1 elms");
	}
}
