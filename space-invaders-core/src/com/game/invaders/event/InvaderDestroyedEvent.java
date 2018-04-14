package com.game.invaders.event;

import com.game.engine.system.event.Event;

public class InvaderDestroyedEvent implements Event {
	private int invader;

	public InvaderDestroyedEvent(int invader) {
		super();
		this.invader = invader;
	}
	public int getInvader() {
		return invader;
	}
	public void setInvader(int invader) {
		this.invader = invader;
	}
}
