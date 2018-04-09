package com.game.invaders.component;

import com.game.engine.entity.AbstractComponent;

public class PlayerShip extends AbstractComponent {
	public enum PlayerState {
		STOPPED,
		MOVING_LEFT,
		MOVING_RIGHT
	}
	
	private PlayerState state;
	private boolean firing;
	private float shootDelay;

	public PlayerShip(PlayerState state, float shootDelay) {
		super(ComponentID.PLAYER_SHIP);
		this.state = state;
		this.shootDelay = shootDelay;
		firing = false;
	}
	public PlayerState getState() {
		return state;
	}
	public void setState(PlayerState state) {
		this.state = state;
	}
	public float getShootDelay() {
		return shootDelay;
	}
	public void setShootDelay(float shootDelay) {
		this.shootDelay = shootDelay;
	}
	public boolean isFiring() {
		return firing;
	}
	public void setFiring(boolean firing) {
		this.firing = firing;
	}
}