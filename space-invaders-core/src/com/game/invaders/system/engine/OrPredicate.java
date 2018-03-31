package com.game.invaders.system.engine;

import java.util.Set;

import com.game.invaders.scene.actor.ActorComponent;

public class OrPredicate implements ComponentPredicate {
	private ComponentPredicate a, b;
	
	public OrPredicate(ComponentPredicate a, ComponentPredicate b) {
		super();
		this.a = a;
		this.b = b;
	}
	public ComponentPredicate getA() {
		return a;
	}
	public void setA(ComponentPredicate a) {
		this.a = a;
	}
	public ComponentPredicate getB() {
		return b;
	}
	public void setB(ComponentPredicate b) {
		this.b = b;
	}
	@Override
	public boolean match(Set<ActorComponent> components) {
		return a.match(components) || b.match(components);
	}
}
