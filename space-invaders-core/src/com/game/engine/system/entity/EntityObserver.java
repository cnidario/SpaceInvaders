package com.game.engine.system.entity;

import com.game.engine.entity.Component;

public interface EntityObserver {
	void entityAdded(int entity);
	void entityDeleted(int entity);
	void componentAdded(int entity, Component component);
	void componentUpdated(int entity, Component component);
	void componentDeleted(int entity, Class<? extends Component> component);
}
