package com.game.invaders.event;

import com.game.engine.system.event.Event;

public class ShootCreatedEvent implements Event {
	private int entity;
	public ShootCreatedEvent(int entity) {
		super();
		this.entity = entity;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
}
