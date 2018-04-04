package com.game.invaders.scene.actor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntSet;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.system.event.Event.EventType;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.types.ActorLifeCycleEvent;
import com.game.invaders.system.event.types.ComponentAddedEvent;
import com.game.invaders.system.event.types.ComponentRemovedEvent;

public class EntityManager {
	private EventManager eventManager; //XXX debería desacoplar ambas? SRP?
	private int lastId;
	private IntSet entities;
	private IntMap<Map<ActorComponentID, ActorComponent>> components;

	public EntityManager(EventManager eventManager) {
		super();
		lastId = 0;
		entities = new IntSet();
		components = new IntMap<Map<ActorComponentID, ActorComponent>>();
		this.eventManager = eventManager;
	}
	
	public int createEntity() {
		entities.add(++lastId);
		components.put(lastId, new HashMap<ActorComponent.ActorComponentID, ActorComponent>());
		eventManager.queueEvent(new ActorLifeCycleEvent(EventType.ACTOR_CREATED, lastId));
		return lastId;
	}
	public void removeEntity(int entity) {
		System.out.println("removed " + entity);
		entities.remove(entity);
		components.remove(entity);
		eventManager.queueEvent(new ActorLifeCycleEvent(EventType.ACTOR_DELETED, entity));
	}
	public void addComponent(int entity, ActorComponent component) {
		Map<ActorComponentID, ActorComponent> entityComps = components.get(entity);
		entityComps.put(component.getID(), component);
		eventManager.queueEvent(new ComponentAddedEvent(entity, component));
	}
	public void removeComponent(int entity, ActorComponent component) {
		Map<ActorComponentID, ActorComponent> entityComps = components.get(entity);
		entityComps.remove(component.getID());
		eventManager.queueEvent(new ComponentRemovedEvent(entity, component));
	}
	public IntSet getEntities() {
		return entities;
	}
	public Set<ActorComponent> componentsFor(int entity) {
		return new HashSet<ActorComponent>(components.get(entity).values());
	}
	public ActorComponent componentFor(int entity, ActorComponentID cid) {
		return components.get(entity).get(cid);
	}
}
