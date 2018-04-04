package com.game.invaders.scene.actor.component;

public class PlayerShipStateC extends AbstractActorComponent {
	public enum PlayerState {
		STOPPED,
		MOVING_LEFT,
		MOVING_RIGHT,
		FIRING
	}
	
	private PlayerState state;
	private float shootDelay;

	public PlayerShipStateC(PlayerState state, float shootDelay) {
		super(ActorComponentID.PLAYER_STATE);
		this.state = state;
		this.shootDelay = shootDelay; 
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
}
