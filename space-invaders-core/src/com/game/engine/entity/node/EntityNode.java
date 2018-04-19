package com.game.engine.entity.node;

public class EntityNode {
	private EntityNodeManager entityNodeManager;
	private int entity;
	
	public EntityNode(EntityNodeManager entityNodeManager, int entity) {
		super();
		this.entityNodeManager = entityNodeManager;
		this.entity = entity;
	}
	public int getEntity() {
		return entity;
	}
	public void remove() {	
	}
	public EntityNode parent() {
		return null;
	}
}
