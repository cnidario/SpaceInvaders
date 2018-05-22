package com.game.engine.entity.observer;

import java.util.ArrayList;
import java.util.List;
import com.game.engine.component.Component;

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
	public void componentAdded(int entity, Component component) {
		for (EntityObserver entityObserver : observers) {
			entityObserver.componentAdded(entity, component);
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
