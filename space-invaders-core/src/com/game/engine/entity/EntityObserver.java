package com.game.engine.entity;

public interface EntityObserver {
	void entityCreated(int entity);
	void entityRemoved(int entity);
	void componentAdded(int entity, Component component);
	void componentRemoved(int entity, Component component);
}
