package com.game.engine.factory;

import com.game.engine.entity.EntityManager;
import com.game.engine.system.entity.EntityBuilder;
import com.game.engine.system.event.EventSystem;

public class EntityBuilderFactory {
	private EntityManager entityManager;
	private EventSystem eventSystem;
	
	public EntityBuilderFactory(EntityManager entityManager, EventSystem eventSystem) {
		super();
		this.entityManager = entityManager;
		this.eventSystem = eventSystem;
	}
	public EntityBuilder create() {
		return new EntityBuilder(entityManager, eventSystem);
	}
}
