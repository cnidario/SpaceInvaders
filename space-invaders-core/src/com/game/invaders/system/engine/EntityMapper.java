package com.game.invaders.system.engine;

import java.util.EnumSet;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.system.event.Event.EventType;
import com.game.invaders.system.event.types.ComponentAddedEvent;
import com.game.invaders.system.event.types.ComponentRemovedEvent;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.EventManager.EventListener;

public class EntityMapper {
	private IntSet group;
	private EventManager eventManager;
	private EntityManager manager;
	private EnumSet<ActorComponentID> components;
	
	public EntityMapper(EntityManager manager, EventManager eventManager, EnumSet<ActorComponentID> components) {
		group = new IntSet();
		this.eventManager = eventManager;
		this.manager = manager;
		this.components = components;
		init();
	}
	private boolean checkEntity(int entity) {
		boolean match = true;
		for (ActorComponentID actorComponentID : components) {
			if(manager.componentFor(entity, actorComponentID) == null) {
				match = false;
				break;
			}
		}
		return match;
	}
	private void load() {
		for(IntSetIterator iter = manager.getEntities().iterator(); iter.hasNext;) {
			int entity = iter.next();
			if(checkEntity(entity))
				group.add(entity);
		}
	}
	private void init() {
		load();
		eventManager.registerHandler(new EventListener() {
			@Override
			public void handle(Event e) {
				int entity = ((ComponentAddedEvent)e).getEntity();
				if(checkEntity(entity))
					group.add(entity);
				else
					group.remove(entity);
			}
		}, EnumSet.of(EventType.COMPONENT_ADDED));
		eventManager.registerHandler(new EventListener() {
			@Override
			public void handle(Event e) {
				int entity = ((ComponentRemovedEvent)e).getEntity();
				if(checkEntity(entity))
					group.add(entity);
				else
					group.remove(entity);
			}
		}, EnumSet.of(EventType.COMPONENT_REMOVED));
	}
	public IntSet getGroup() {
		return group;
	}
}
