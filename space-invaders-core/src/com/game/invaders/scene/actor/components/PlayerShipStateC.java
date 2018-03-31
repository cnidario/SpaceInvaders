package com.game.invaders.scene.actor.components;

public class PlayerShipStateC extends AbstractActorComponent {
	public enum PlayerState {
		STOPPED,
		MOVING_LEFT,
		MOVING_RIGHT,
		FIRING
	}
	
	private PlayerState state;

	public PlayerShipStateC(PlayerState state) {
		super(ActorComponentID.PLAYER_STATE);
		this.state = state;
	}
	public PlayerState getState() {
		return state;
	}
	public void setState(PlayerState state) {
		this.state = state;
	}
}
