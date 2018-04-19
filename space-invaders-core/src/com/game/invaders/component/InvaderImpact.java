package com.game.invaders.component;

import com.game.engine.entity.Component;

public class InvaderImpact implements Component {
	private int invader;
	private float time;
	
	public InvaderImpact(int invader, float time) {
		super();
		this.invader = invader;
		this.time = time;
	}
	public int getInvader() {
		return invader;
	}
	public void setInvader(int invader) {
		this.invader = invader;
	}
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}
}
