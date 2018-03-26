package com.game.invaders.scene.actor.player;

import com.game.invaders.scene.actor.Actor;
import com.game.invaders.statemachine.StateMachine;

public class PlayerStateMachine implements StateMachine<PlayerState> {
	public enum PlayerStateID {
		NO_MOVING,
		MOVING_LEFT,
		MOVING_RIGHT,
		SHOOT
	}
	
	public class PlayerMovingState implements PlayerState {
		@Override
		public void update(float dt, Actor actor) {
			// TODO Auto-generated method stub
		}
		@Override
		public void enter() {
			// TODO Auto-generated method stub
		}
		@Override
		public void exit() {
			// TODO Auto-generated method stub
		}
	}
	
	private PlayerState state;

	public PlayerState getState() {
		return state;
	}
	public void setState(PlayerState state) {
		this.state = state;
	}
	@Override
	public void requestStateChange(PlayerState state) {
		setState(state);
	}
	@Override
	public void update(float dt, Actor actor) {
		state.update(dt, actor);
	}
}
