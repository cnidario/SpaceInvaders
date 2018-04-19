package com.game.engine.entity;

import java.util.ArrayList;
import java.util.List;

public class EntitySubscribers implements EntityObserver {
	private List<EntityObserver> observers = new ArrayList<EntityObserver>();
	public void attach(EntityObserver o) {
		observers.add(o);
	}
	public void detach(EntityObserver o) {
		observers.remove(o);
	}
	@Override
	public void entityCreated(int entity) {
		for (EntityObserver entityObserver : observers) {
			entityObserver.entityCreated(entity);
		}
	}
	@Override
	public void entityRemoved(int entity) {
		for (EntityObserver entityObserver : observers) {
			entityObserver.entityRemoved(entity);
		}
	}
	@Override
	public void componentAdded(int entity, Component component) {
		for (EntityObserver entityObserver : observers) {
			entityObserver.componentAdded(entity, component);
		}
	}
	@Override
	public void componentRemoved(int entity, Component component) {
		for (EntityObserver entityObserver : observers) {
			entityObserver.componentRemoved(entity, component);
		}
	}
}
