package com.game.invaders.scene.actor.components;

import com.game.invaders.system.logic.invader.InvaderBehaviourSystem.InvaderStateID;

public class InvaderStateC extends AbstractActorComponent {
	private InvaderStateID stateID;
	
	public InvaderStateC() {
		super(ActorComponentID.INVADER_STATE);
	}
	public InvaderStateID getStateID() {
		return stateID;
	}
	public void setStateID(InvaderStateID stateID) {
		this.stateID = stateID;
	}
}
