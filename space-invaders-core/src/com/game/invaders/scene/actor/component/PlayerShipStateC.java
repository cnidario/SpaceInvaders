package com.game.invaders.scene.actor.component;

public class PlayerShipStateC extends AbstractActorComponent {
	public enum PlayerState {
		STOPPED,
		MOVING_LEFT,
		MOVING_RIGHT
	}
	
	private PlayerState state;
	private boolean firing;
	private float shootDelay;

	public PlayerShipStateC(PlayerState state, float shootDelay) {
		super(ActorComponentID.PLAYER_STATE);
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
