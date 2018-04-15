package com.game.engine.system.event.types;

import com.game.engine.entity.Component;
import com.game.engine.system.event.Event;

public class ComponentAddedEvent implements Event {
	private int entity;
	private Class<? extends Component> component;
	
	public ComponentAddedEvent(int entity, Class<? extends Component> component) {
		this.entity = entity;
		this.component = component;
	}
	public Class<? extends Component> getComponent() {
		return component;
	}
	public void setComponent(Class<? extends Component> component) {
		this.component = component;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
}
