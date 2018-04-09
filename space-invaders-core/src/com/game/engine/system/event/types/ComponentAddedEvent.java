package com.game.engine.system.event.types;

import com.game.engine.entity.Component;
import com.game.engine.system.event.Event;

public class ComponentAddedEvent extends Event {
	private int entity;
	private Component component;
	
	public ComponentAddedEvent(int entity, Component component) {
		super(Event.EventType.COMPONENT_ADDED);
		this.entity = entity;
		this.component = component;
	}
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
}
