package com.game.engine.system.event.types;

import com.game.engine.system.event.Event;

public class ActorAddedEvent implements Event {
	private int entity;
	public ActorAddedEvent(int entity) {
		this.entity = entity;
	}
	public int getEntity() {
		return entity;
	}
}
