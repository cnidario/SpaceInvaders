package com.game.engine.factory;

import com.game.engine.entity.EntityManager;
import com.game.engine.system.entity.EntityBuilder;

public class EntityBuilderFactory {
	private EntityManager entityManager;
	
	public EntityBuilderFactory(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	public EntityBuilder create() {
		return new EntityBuilder(entityManager);
	}
}
