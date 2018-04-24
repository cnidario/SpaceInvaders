package com.game.engine.system.entity.node;

import com.game.engine.entity.Component;

public class EntityNode implements Node {
	private EntityNodeMediator entityNodeMediator;
	private int entity;
	
	public EntityNode(EntityNodeMediator entityNodeMediator, int entity) {
		super();
		this.entityNodeMediator = entityNodeMediator;
		this.entity = entity;
	}
	public int getEntity() {
		return entity;
	}
	@Override
	public void delete() {
		entityNodeMediator.deleteEntity(entity);
	}
	@Override
	public Component component(Class<? extends Component> component) {
		return entityNodeMediator.component(entity, component);
	}
	@Override
	public Component update(Component component) {
		entityNodeMediator.updateEntity(entity, component);
		return component;
	}
	@Override
	public void deleteComponent(Class<? extends Component> component) {
		entityNodeMediator.deleteComponent(entity, component);
	}
}
