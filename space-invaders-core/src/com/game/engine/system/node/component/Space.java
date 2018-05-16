package com.game.engine.system.node.component;

import com.game.engine.component.Component;

public class Space implements Component {
	private int id;

	public Space(int id) {
		super();
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
