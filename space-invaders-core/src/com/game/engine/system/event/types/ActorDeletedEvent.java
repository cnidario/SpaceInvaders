package com.game.engine.system.event.types;

import com.game.engine.system.event.Event;

public class ActorDeletedEvent implements Event {
	private int entity;
	public ActorDeletedEvent(int entity) {
		this.entity = entity;
	}
	public int getEntity() {
		return entity;
	}
}
