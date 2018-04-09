package com.game.engine.entity.component;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.AbstractComponent;

public class Position extends AbstractComponent {
	private Vector2 pos;

	public Position(Vector2 pos) {
		super(ComponentID.POSITION);
		this.pos = pos;
	}
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
}
