package com.game.invaders.scene.actor;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntSet;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.system.event.EventManager;

public class EntityManager {
	private EventManager event_manager; //XXX debería desacoplar ambas? SRP?
	private int lastId;
	private IntSet entities;
	private IntMap<Map<ActorComponentID, ActorComponent>> components;

	public EntityManager(EventManager event_manager) {
		super();
		lastId = 0;
		entities = new IntSet();
		components = new IntMap<Map<ActorComponentID, ActorComponent>>();
		this.event_manager = event_manager;
	}
	
	public int createEntity() {
		entities.add(++lastId);
		components.put(lastId, new HashMap<ActorComponent.ActorComponentID, ActorComponent>());
		//TODO enviar evento
		return lastId;
	}
	public void removeEntity(int entity) {
		entities.remove(entity);
		components.remove(entity);
		//TODO eventos
	}
	public void addComponent(int entity, ActorComponent component) {
		Map<ActorComponentID, ActorComponent> entityComps = components.get(entity);
		entityComps.put(component.getID(), component);
		//TODO eventos
	}
	public void removeComponent(int entity, ActorComponent component) {
		Map<ActorComponentID, ActorComponent> entityComps = components.get(entity);
		entityComps.remove(component.getID());
		//TODO eventos
	}
}
