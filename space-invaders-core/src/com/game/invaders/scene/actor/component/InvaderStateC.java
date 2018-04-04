package com.game.invaders.scene.actor.component;

public class InvaderStateC extends AbstractActorComponent {
	public enum InvaderStateID {
		ALIVE, DYING
	}
	
	private InvaderStateID stateID;
	private float dyingTime;
	
	public InvaderStateC(InvaderStateID stateID) {
		super(ActorComponentID.INVADER_STATE);
		this.stateID = stateID;
		dyingTime = 0;
	}
	public InvaderStateID getStateID() {
		return stateID;
	}
	public void setStateID(InvaderStateID stateID) {
		this.stateID = stateID;
	}
	public float getDyingTime() {
		return dyingTime;
	}
	public void setDyingTime(float dyingTime) {
		this.dyingTime = dyingTime;
	}
}
