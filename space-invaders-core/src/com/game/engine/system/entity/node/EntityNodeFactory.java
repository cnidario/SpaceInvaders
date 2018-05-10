package com.game.engine.system.entity.node;

import com.game.engine.system.entity.EntityDAO;

public class EntityNodeFactory {
	private EntityDAO entityDAO;

	public EntityNodeFactory(EntityDAO entityDAO) {
		super();
		this.entityDAO = entityDAO;
	}
	public EntityNode create(int entity) {
		return new EntityNode(entityDAO, this, entity);
	}
}
