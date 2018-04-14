package com.game.engine.system.event.types;

import com.game.engine.system.event.Event;

public class EntityAddedEvent implements Event {
	private int entity;
	public EntityAddedEvent(int entity) {
		this.entity = entity;
	}
	public int getEntity() {
		return entity;
	}
}
