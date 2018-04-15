package com.game.invaders.system.impact;

import com.game.engine.entity.EntityManager;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.EventSystem.EventListener;
import com.game.engine.system.event.types.CollisionEvent;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.factory.ImpactEventFactory;

public class ImpactDetectionSystem extends AbstractProcess {
	private EntityManager manager;
	private EventSystem eventManager;
	private ImpactEventFactory impactFactory;
	
	public ImpactDetectionSystem(EntityManager manager, EventSystem eventManager, ImpactEventFactory impactFactory) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		this.impactFactory = impactFactory;
	}
	@Override
	public void init() {
		eventManager.registerHandler(new EventListener<CollisionEvent>() {
			@Override
			public void handle(CollisionEvent ev) {
				int shoot = ev.getEntity1();
				int invader = ev.getEntity2();
				impactFactory.create(invader, shoot);
			}
		}, CollisionEvent.class);
	}
	@Override
	public void update(float dt) {
		
	}
}
