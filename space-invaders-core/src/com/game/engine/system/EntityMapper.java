package com.game.engine.system;

import java.util.EnumSet;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.Component.ComponentID;
import com.game.engine.system.event.Event;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.Event.EventType;
import com.game.engine.system.event.EventSystem.EventListener;
import com.game.engine.system.event.types.ActorLifeCycleEvent;
import com.game.engine.system.event.types.ComponentAddedEvent;
import com.game.engine.system.event.types.ComponentRemovedEvent;

public class EntityMapper {
	private IntSet group;
	private EventSystem eventManager;
	private EntityManager manager;
	private EnumSet<ComponentID> components;
	
	public EntityMapper(EntityManager manager, EventSystem eventManager, EnumSet<ComponentID> components) {
		group = new IntSet();
		this.eventManager = eventManager;
		this.manager = manager;
		this.components = components;
		init();
	}
	private boolean checkEntity(int entity) {
		boolean match = true;
		for (ComponentID actorComponentID : components) {
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
		eventManager.registerHandler(new EventListener() {
			@Override
			public void handle(Event e) {
				int entity = ((ActorLifeCycleEvent)e).getEntity();
				group.remove(entity);
			}
		}, EnumSet.of(EventType.ACTOR_DELETED));
	}
	public IntSet getGroup() {
		return group;
	}
	public int one() {
		if(group.size == 0)
			return -1;
		else
			return group.first();
	}
}