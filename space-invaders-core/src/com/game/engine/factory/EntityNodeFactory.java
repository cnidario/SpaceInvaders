package com.game.engine.factory;

import com.game.engine.entity.EntityDAO;
import com.game.engine.system.node.impl.EntityNode;

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
