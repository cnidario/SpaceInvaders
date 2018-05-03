package com.game.engine.system.entity;

import java.util.Set;

import com.game.engine.entity.Component;

public interface EntityObserver {
	void entityAdded(int entity);
	void entityDeleted(int entity);
	void componentAdded(int entity, Set<Component> components, Component component);
	void componentUpdated(int entity, Component component);
	void componentDeleted(int entity, Class<? extends Component> component);
}
