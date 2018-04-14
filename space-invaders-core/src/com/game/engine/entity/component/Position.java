package com.game.engine.entity.component;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.Component;

public class Position implements Component {
	private Vector2 pos;

	public Position(Vector2 pos) {
		this.pos = pos;
	}
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
}
