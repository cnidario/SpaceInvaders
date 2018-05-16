package com.game.engine.component;

import com.badlogic.gdx.math.Vector2;

public class GroupParent implements Component {
	private Vector2 offset;
	private int parent;
	
	public GroupParent(Vector2 offset, int parent) {
		this.offset = offset;
		this.parent = parent;
	}
	public Vector2 getOffset() {
		return offset;
	}
	public void setOffset(Vector2 offset) {
		this.offset = offset;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
}
