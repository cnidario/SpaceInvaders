package com.game.engine.entity.node.component;

import com.game.engine.entity.Component;

public class RemoveComponent implements Component {
	private int entity;
	private Class<? extends Component> clazz;
	
	public RemoveComponent(int entity, Class<? extends Component> clazz) {
		super();
		this.entity = entity;
		this.clazz = clazz;
	}
	public int getEntity() {
		return entity;
	}
	public void setEntity(int entity) {
		this.entity = entity;
	}
	public Class<? extends Component> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends Component> clazz) {
		this.clazz = clazz;
	}
}
