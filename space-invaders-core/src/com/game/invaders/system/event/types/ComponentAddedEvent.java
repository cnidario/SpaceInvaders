package com.game.invaders.system.event.types;

import com.game.invaders.scene.actor.ActorComponent;
import com.game.invaders.system.event.Event;

public class ComponentAddedEvent extends Event {
	private int entity;
	private ActorComponent component;
	
	public ComponentAddedEvent(int entity, ActorComponent component) {
		super(Event.EventType.COMPONENT_ADDED);
		this.entity = entity;
		this.component = component;
	}
	public ActorComponent getComponent() {
		return component;
	}
	public void setComponent(ActorComponent component) {
		this.component = component;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
}
