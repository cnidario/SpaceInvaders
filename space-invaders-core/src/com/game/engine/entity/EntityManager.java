package com.game.engine.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntSet;
import com.game.engine.system.event.EventSystem;
import com.game.engine.entity.Component.ComponentID;
import com.game.engine.system.event.types.ActorDeletedEvent;
import com.game.engine.system.event.types.ComponentAddedEvent;
import com.game.engine.system.event.types.ComponentRemovedEvent;

public class EntityManager {
	private EventSystem eventManager; //XXX debería desacoplar ambas? SRP?
	private int lastId;
	private IntSet entities;
	private IntMap<Map<ComponentID, Component>> components;

	public EntityManager(EventSystem eventManager) {
		super();
		lastId = 0;
		entities = new IntSet();
		components = new IntMap<Map<ComponentID, Component>>();
		this.eventManager = eventManager;
	}
	
	public int createEntity() {
		entities.add(++lastId);
		components.put(lastId, new HashMap<Component.ComponentID, Component>());
		// XXX eventManager.queueEvent(new ActorAddedEvent(lastId)); <- ahora mismo en builder
		return lastId;
	}
	public void markEntityForRemove(int entity) {
		eventManager.queueEvent(new ActorDeletedEvent(entity));
	}
	public void removeEntity(int entity) {
		entities.remove(entity);
		components.remove(entity);
	}
	public void addComponent(int entity, Component component) {
		Map<ComponentID, Component> entityComps = components.get(entity);
		entityComps.put(component.getID(), component);
		eventManager.queueEvent(new ComponentAddedEvent(entity, component));
	}
	public void removeComponent(int entity, Component component) {
		Map<ComponentID, Component> entityComps = components.get(entity);
		entityComps.remove(component.getID());
		eventManager.queueEvent(new ComponentRemovedEvent(entity, component));
	}
	public IntSet getEntities() {
		return entities;
	}
	public Set<Component> componentsFor(int entity) {
		return new HashSet<Component>(components.get(entity).values());
	}
	public Component componentFor(int entity, ComponentID cid) {
		return components.get(entity).get(cid);
	}
}
