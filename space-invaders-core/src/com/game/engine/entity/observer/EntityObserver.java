package com.game.engine.entity.observer;

import java.util.Set;

import com.game.engine.entity.Component;

public interface EntityObserver {
	void entityDeleted(int entity);
	void componentAdded(int entity, Set<Component> components, Component component);
	void componentUpdated(int entity, Component component);
	void componentDeleted(int entity, Class<? extends Component> component);
}
