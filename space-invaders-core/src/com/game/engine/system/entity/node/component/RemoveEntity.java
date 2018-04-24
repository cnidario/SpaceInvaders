package com.game.engine.system.entity.node.component;

import com.game.engine.entity.Component;

public class RemoveEntity implements Component {
	private int entity;

	public RemoveEntity(int entity) {
		super();
		this.entity = entity;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
}
