package com.game.invaders.system.engine;

import java.util.EnumSet;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.system.event.Event.EventType;
import com.game.invaders.system.event.types.ComponentAddedEvent;
import com.game.invaders.system.event.types.ComponentRemovedEvent;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.EventManager.EventListener;

public class EntityPredicateGroup implements EntityGroup {
	private IntSet group;
	private ComponentPredicate predicate;
	private EventManager eventManager;
	private EntityManager manager;
	
	public EntityPredicateGroup(EntityManager manager, ComponentPredicate predicate, EventManager eventManager) {
		group = new IntSet();
		this.predicate = predicate;
		this.eventManager = eventManager;
		this.manager = manager;
		init();
	}
	private void load() {
		for(IntSetIterator iter = manager.getEntities().iterator(); iter.hasNext;) {
			int entity = iter.next();
			if(predicate.match(manager.componentsFor(entity))) {
				group.add(entity);
			}
		}
	}
	private void init() {
		load();
		eventManager.registerHandler(new EventListener() {
			@Override
			public void handle(Event e) {
				int entity = ((ComponentAddedEvent)e).getEntity();
				if(predicate.match(manager.componentsFor(entity)))
					group.add(entity);
				else
					group.remove(entity);
			}
		}, EnumSet.of(EventType.COMPONENT_ADDED));
		eventManager.registerHandler(new EventListener() {
			@Override
			public void handle(Event e) {
				int entity = ((ComponentRemovedEvent)e).getEntity();
				if(predicate.match(manager.componentsFor(entity)))
					group.add(entity);
				else
					group.remove(entity);
			}
		}, EnumSet.of(EventType.COMPONENT_REMOVED));
	}
	@Override
	public IntSet getGroup() {
		return group;
	}
}
