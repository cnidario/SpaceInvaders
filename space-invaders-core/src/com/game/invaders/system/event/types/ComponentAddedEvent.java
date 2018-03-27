package com.game.invaders.system.event.types;

import com.game.invaders.scene.actor.ActorComponent;
import com.game.invaders.system.event.Event;

public class ComponentAddedEvent extends Event {
	private ActorComponent component;
	public ComponentAddedEvent(ActorComponent component) {
		super(Event.EventType.COMPONENT_ADDED);
		this.component = component;
	}
	public ActorComponent getComponent() {
		return component;
	}
	public void setComponent(ActorComponent component) {
		this.component = component;
	}
}
