package com.game.invaders.scene.actor.components;

import com.game.invaders.scene.actor.Actor;
import com.game.invaders.statemachine.State;
import com.game.invaders.statemachine.StateMachine;

public class StateMachineActorC<S extends State> extends AbstractActorComponent {
	private StateMachine<S> stateMachine;
	public StateMachine<S> getStateMachine() {
		return stateMachine;
	}
	public void setStateMachine(StateMachine<S> stateMachine) {
		this.stateMachine = stateMachine;
	}
	public StateMachineActorC(Actor parent, StateMachine<S> stateMachine) {
		super(parent, ActorComponentID.STATE_MACHINE);
		this.stateMachine = stateMachine;
	}
}
