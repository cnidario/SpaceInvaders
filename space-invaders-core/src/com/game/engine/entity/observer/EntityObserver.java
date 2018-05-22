package com.game.engine.entity.observer;

import com.game.engine.component.Component;

public interface EntityObserver {
	void entityDeleted(int entity);
	void componentAdded(int entity, Component component);
	void componentUpdated(int entity, Component component);
	void componentDeleted(int entity, Class<? extends Component> component);
}
