package com.game.invaders.scene.actor.components;

import com.badlogic.gdx.math.Vector2;

public class ChildOfGroupC extends AbstractActorComponent {
	private Vector2 offset;
	private int parent;
	
	public ChildOfGroupC(Vector2 offset, int parent) {
		super(ActorComponentID.GROUP_CHILD);
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
