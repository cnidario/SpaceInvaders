package com.game.engine.system.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.game.engine.entity.Component;

public class EntityNotifier implements EntityObserver, EntitySubscriber {
	private List<EntityObserver> observers = new ArrayList<EntityObserver>();
	
	@Override
	public void attach(EntityObserver o) {
		observers.add(o);
	}
	@Override
	public void detach(EntityObserver o) {
		observers.remove(o);
	}
	@Override
	public void entityDeleted(int entity) {
		for (EntityObserver entityObserver : observers) {
			entityObserver.entityDeleted(entity);
		}
	}
	@Override
	public void componentAdded(int entity, Set<Component> components, Component component) {
		for (EntityObserver entityObserver : observers) {
			entityObserver.componentAdded(entity, null, component);
		}
	}
	@Override
	public void componentUpdated(int entity, Component component) {
		for (EntityObserver entityObserver : observers) {
			entityObserver.componentUpdated(entity, component);
		}
	}
	@Override
	public void componentDeleted(int entity, Class<? extends Component> component) {
		for (EntityObserver entityObserver : observers) {
			entityObserver.componentDeleted(entity, component);
		}
	}
}
