package com.game.engine.system.base;

import java.util.EnumSet;
import com.badlogic.gdx.utils.IntArray;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.event.Event;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.Event.EventType;
import com.game.engine.system.event.EventSystem.EventListener;
import com.game.engine.system.event.types.ActorLifeCycleEvent;
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
		eventManager.registerHandler(new EventListener() {
			@Override
			public void handle(Event e) {
				ActorLifeCycleEvent ev = (ActorLifeCycleEvent) e;
				toDelete.add(ev.getEntity());
			}
		}, EnumSet.of(EventType.ACTOR_DELETED));
	}
}
