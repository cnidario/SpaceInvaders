package com.game.invaders.event;

import com.game.engine.system.event.Event;

public class InvaderImpactedEvent implements Event {
	private int invader, shoot;

	public InvaderImpactedEvent(int invader, int shoot) {
		super();
		this.invader = invader;
		this.shoot = shoot;
	}
	public int getInvader() {
		return invader;
	}
	public void setInvader(int invader) {
		this.invader = invader;
	}
	public int getShoot() {
		return shoot;
	}
	public void setShoot(int shoot) {
		this.shoot = shoot;
	}
}
