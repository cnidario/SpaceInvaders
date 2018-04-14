package com.game.engine.system.event.types;

import com.game.engine.system.event.Event;

public class EntityRemovedEvent implements Event {
	private int entity;
	public EntityRemovedEvent(int entity) {
		this.entity = entity;
	}
	public int getEntity() {
		return entity;
	}
}
