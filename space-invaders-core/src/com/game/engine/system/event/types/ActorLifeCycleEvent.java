package com.game.engine.system.event.types;

import com.game.engine.system.event.Event;

public class ActorLifeCycleEvent extends Event {
	private int entity;
	public ActorLifeCycleEvent(EventType type, int entity) {
		super(type);
		this.entity = entity;
	}
	public int getEntity() {
		return entity;
	}
}
