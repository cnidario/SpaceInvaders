package com.game.invaders.system.event.types;

import com.game.invaders.system.collision.CollisionManager.CollisionEntity;
import com.game.invaders.system.event.Event;

public class CollisionEvent extends Event {
	private CollisionEntity e1, e2;
	public CollisionEvent(CollisionEntity e1, CollisionEntity e2) {
		super(Event.EventType.COLLISION);
		this.e1 = e1;
		this.e2 = e2;
	}
	public CollisionEntity getEntity1() {
		return e1;
	}
	public CollisionEntity getEntity2() {
		return e2;
	}
}
