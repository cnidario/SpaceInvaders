package com.game.engine.system.entity.node;

public class EntityNodeFactory {
	private EntityNodeMediator entityNodeMediator;

	public EntityNodeFactory(EntityNodeMediator entityNodeMediator) {
		super();
		this.entityNodeMediator = entityNodeMediator;
	}
	public EntityNode create(int entity) {
		return new EntityNode(entityNodeMediator, entity);
	}
}
