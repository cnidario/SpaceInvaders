package com.game.engine.system.entity.lifecycle;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.IntArray;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.EventSystem.EventListener;
import com.game.engine.system.event.types.EntityRemovedEvent;
import com.game.engine.system.event.types.ComponentRemovedEvent;
import com.game.engine.system.process.AbstractProcess;

public class EntityLifeCycleSyystem extends AbstractProcess {
	private EntityManager manager;
	private EventSystem eventManager;
	private IntArray toDelete;
	private List<ComponentRemovedEvent> toDeleteComps;
	
	public EntityLifeCycleSyystem(EntityManager manager, EventSystem eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		toDelete = new IntArray();
		toDeleteComps = new ArrayList<ComponentRemovedEvent>();
	}
	@Override
	public void update(float dt) {
		for(int e : toDelete.items) {
			manager.removeEntity(e);
		}
		for(ComponentRemovedEvent e : toDeleteComps) {
			manager.removeComponent(e.getEntity(), e.getComponent());
		}
		toDelete.clear();
		toDeleteComps.clear();
	}
	@Override
	public void init() {
		eventManager.registerHandler(new EventListener<EntityRemovedEvent>() {
			@Override
			public void handle(EntityRemovedEvent ev) {
				toDelete.add(ev.getEntity());
			}
		}, EntityRemovedEvent.class);
		eventManager.registerHandler(new EventListener<ComponentRemovedEvent>() {
			@Override
			public void handle(ComponentRemovedEvent e) {
				toDeleteComps.add(e);
			}
		}, ComponentRemovedEvent.class);
	}
}
