package com.game.engine.system.node.component;

import com.game.engine.component.Component;

public class SceneNode implements Component {
	public static final int ABSOLUTE_ROOT = -1;
	private int parent;

	public SceneNode(int parent) {
		super();
		this.parent = parent;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
}
