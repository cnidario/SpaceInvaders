package com.game.invaders.scene.actor.components;

public class InvaderStateC extends AbstractActorComponent {
	public enum InvaderStateID {
		ALIVE, DYING, DIED
	}
	
	private InvaderStateID stateID;
	
	public InvaderStateC(InvaderStateID stateID) {
		super(ActorComponentID.INVADER_STATE);
		this.stateID = stateID;
	}
	public InvaderStateID getStateID() {
		return stateID;
	}
	public void setStateID(InvaderStateID stateID) {
		this.stateID = stateID;
	}
}
