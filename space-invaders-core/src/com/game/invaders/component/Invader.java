package com.game.invaders.component;

import com.game.engine.entity.AbstractComponent;

public class Invader extends AbstractComponent {
	public enum InvaderStateID {
		ALIVE, DYING
	}
	
	private InvaderStateID stateID;
	private float dyingTime;
	
	public Invader(InvaderStateID stateID) {
		super(ComponentID.INVADER);
		this.stateID = stateID;
		dyingTime = 0; //XXX?
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
