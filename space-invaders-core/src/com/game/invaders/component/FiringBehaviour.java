package com.game.invaders.component;

import com.game.engine.component.Component;

public class FiringBehaviour implements Component {
	private boolean firing;
	private float shootDelay;
	private float shootTimeLeft;
	
	public FiringBehaviour(boolean firing, float shootDelay) {
		super();
		this.firing = firing;
		this.shootDelay = shootDelay;
		shootTimeLeft = 0;
	}
	public boolean isFiring() {
		return firing;
	}
	public void setFiring(boolean firing) {
		this.firing = firing;
	}
	public float getShootDelay() {
		return shootDelay;
	}
	public void setShootDelay(float shootDelay) {
		this.shootDelay = shootDelay;
	}
	public float getShootTimeLeft() {
		return shootTimeLeft;
	}
	public void setShootTimeLeft(float shootTimeLeft) {
		this.shootTimeLeft = shootTimeLeft;
	}
}
