package com.game.engine.system.base;

import com.badlogic.gdx.utils.IntArray;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.EventSystem.EventListener;
import com.game.engine.system.event.types.ActorDeletedEvent;
import com.game.engine.system.process.AbstractProcess;

public class EntityLifeCycleSystem extends AbstractProcess {
	private EntityManager manager;
	private EventSystem eventManager;
	private IntArray toDelete;
	
	public EntityLifeCycleSystem(EntityManager manager, EventSystem eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		toDelete = new IntArray();
	}
	@Override
	public void update(float dt) {
		for(int e : toDelete.items) {
			manager.removeEntity(e);
		}
	}
	@Override
	public void init() {
		eventManager.registerHandler(new EventListener<ActorDeletedEvent>() {
			@Override
			public void handle(ActorDeletedEvent ev) {
				toDelete.add(ev.getEntity());
			}
		}, ActorDeletedEvent.class);
	}
}
