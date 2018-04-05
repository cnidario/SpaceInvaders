package com.game.invaders.system;

import java.util.EnumSet;
import com.badlogic.gdx.utils.IntArray;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.Event.EventType;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.EventManager.EventListener;
import com.game.invaders.system.event.types.ActorLifeCycleEvent;
import com.game.invaders.system.process.AbstractProcess;

public class EntityLifeCycleSystem extends AbstractProcess {
	private EntityManager manager;
	private EventManager eventManager;
	private IntArray toDelete;
	
	public EntityLifeCycleSystem(EntityManager manager, EventManager eventManager) {
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
