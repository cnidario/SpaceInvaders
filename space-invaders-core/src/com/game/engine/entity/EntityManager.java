package com.game.engine.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntSet;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.types.EntityRemovedEvent;
import com.game.engine.system.event.types.ComponentAddedEvent;
import com.game.engine.system.event.types.ComponentRemovedEvent;

public class EntityManager {
	private EventSystem eventManager;
	private int lastId;
	private IntSet entities;
	private IntMap<Map<Class<? extends Component>, Component>> components;

	public EntityManager(EventSystem eventManager) {
		super();
		lastId = 0;
		entities = new IntSet();
		components = new IntMap<Map<Class<? extends Component>, Component>>();
		this.eventManager = eventManager;
	}
	
	public int createEntity() {
		entities.add(++lastId);
		components.put(lastId, new HashMap<Class<? extends Component>, Component>());
		// XXX eventManager.queueEvent(new ActorAddedEvent(lastId)); <- ahora mismo en builder
		return lastId;
	}
	public void addComponent(int entity, Component component) {
		Map<Class<? extends Component>, Component> entityComps = components.get(entity);
		entityComps.put(component.getClass(), component);
		eventManager.queueEvent(new ComponentAddedEvent(entity, component));
	}
	public void markEntityForRemove(int entity) {
		eventManager.queueEvent(new EntityRemovedEvent(entity));
	}
	public void markComponentForRemove(int entity, Component component) {
		eventManager.queueEvent(new ComponentRemovedEvent(entity, component));
	}
	public void removeEntity(int entity) {
		entities.remove(entity);
		components.remove(entity);
	}
	public void removeComponent(int entity, Component component) {
		Map<Class<? extends Component>, Component> entityComps = components.get(entity);
		if(entityComps != null)
			entityComps.remove(component.getClass());
	}
	public IntSet getEntities() {
		return entities;
	}
	public Set<Component> componentsFor(int entity) {
		return new HashSet<Component>(components.get(entity).values());
	}
	public Component componentFor(int entity, Class<? extends Component> clazz) {
		return components.get(entity).get(clazz);
	}
}
