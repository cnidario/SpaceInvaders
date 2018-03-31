package com.game.invaders.system.event.types;

import com.game.invaders.system.event.Event;

public class CollisionEvent extends Event {
	private int e1, e2;
	public CollisionEvent(int e1, int e2) {
		super(Event.EventType.COLLISION);
		this.e1 = e1;
		this.e2 = e2;
	}
	public int getEntity1() {
		return e1;
	}
	public int getEntity2() {
		return e2;
	}
}
