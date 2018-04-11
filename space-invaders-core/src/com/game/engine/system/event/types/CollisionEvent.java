package com.game.engine.system.event.types;

import com.game.engine.system.event.Event;

public class CollisionEvent implements Event {
	private int e1, e2;
	public CollisionEvent(int e1, int e2) {
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
