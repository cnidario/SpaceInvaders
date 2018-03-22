package com.game.invaders.actor.invader;

import com.game.invaders.actor.Actor;
import com.game.invaders.statemachine.StateMachine;

public class InvaderStateMachine implements StateMachine<InvaderState> {
	public enum InvaderStateID {
		ALIVE, DYING, DIED
	}
	
	private InvaderState state = new AliveInvaderState(this);
	public InvaderState getState() {
		return state;
	}
	public void setState(InvaderState state) {
		this.state = state;
	}
	public void requestStateChange(InvaderState state) {
		//chequea validez de cambios de estado, aquí trivial
		setState(state);
	}
	public void update(float dt, Actor self) {
		state.update(dt, self);
	}
}
