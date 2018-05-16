package com.game.invaders.component;

import com.game.engine.component.Component;

public class PlayerShip implements Component {
	public enum PlayerState {
		STOPPED,
		MOVING_LEFT,
		MOVING_RIGHT
	}
	
	private PlayerState state;

	public PlayerShip(PlayerState state) {
		this.state = state;
	}
	public PlayerState getState() {
		return state;
	}
	public void setState(PlayerState state) {
		this.state = state;
	}
}
