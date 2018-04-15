package com.game.engine.system;

import java.util.Set;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.Component;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.EventSystem.EventListener;
import com.game.engine.system.event.types.EntityRemovedEvent;
import com.game.engine.system.event.types.ComponentAddedEvent;
import com.game.engine.system.event.types.ComponentRemovedEvent;

public class EntityMapper {
	private IntSet group;
	private EventSystem eventManager;
	private EntityManager manager;
	private Set<Class<? extends Component>> components;
	
	public EntityMapper(EntityManager manager, EventSystem eventManager, Set<Class<? extends Component>> components) {
		group = new IntSet();
		this.eventManager = eventManager;
		this.manager = manager;
		this.components = components;
		init();
	}
	private boolean checkEntity(int entity) {
		for (Class<? extends Component> clazz : components)
			if(manager.componentFor(entity, clazz) == null)
				return false;
		return true;
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
		eventManager.registerHandler(new EventListener<ComponentAddedEvent>() {
			@Override
			public void handle(ComponentAddedEvent e) {
				int entity = e.getEntity();
				if(!group.contains(entity) && checkEntity(entity))
					group.add(entity);
			}
		}, ComponentAddedEvent.class);
		eventManager.registerHandler(new EventListener<ComponentRemovedEvent>() {
			@Override
			public void handle(ComponentRemovedEvent e) {
				int entity = e.getEntity();
				if(group.contains(entity) && components.contains(e.getComponent()))
					group.remove(entity);
			}
		}, ComponentRemovedEvent.class);
		eventManager.registerHandler(new EventListener<EntityRemovedEvent>() {
			@Override
			public void handle(EntityRemovedEvent e) {
				int entity = e.getEntity();
				if(group.contains(entity))
					group.remove(entity);
			}
		}, EntityRemovedEvent.class);
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
