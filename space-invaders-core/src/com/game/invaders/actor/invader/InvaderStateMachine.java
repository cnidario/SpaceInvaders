package com.game.invaders.actor.invader;

public class InvaderStateMachine {
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
	public void requestChangeState(InvaderState state) {
		//chequea validez de cambios de estado, aquí trivial
		setState(state);
	}
	public void update(float dt, Invader self) {
		state.update(dt, self);
	}
}
