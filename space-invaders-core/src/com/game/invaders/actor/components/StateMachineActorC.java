package com.game.invaders.actor.components;

import com.game.invaders.actor.ActorComponent;
import com.game.invaders.statemachine.State;
import com.game.invaders.statemachine.StateMachine;

public class StateMachineActorC<S extends State> implements ActorComponent {
	private StateMachine<S> stateMachine;
	public StateMachine<S> getStateMachine() {
		return stateMachine;
	}
	public void setStateMachine(StateMachine<S> stateMachine) {
		this.stateMachine = stateMachine;
	}
	public StateMachineActorC(StateMachine<S> stateMachine) {
		super();
		this.stateMachine = stateMachine;
	}
	@Override
	public ActorComponentID getID() { return ActorComponentID.STATE_MACHINE; }
}
