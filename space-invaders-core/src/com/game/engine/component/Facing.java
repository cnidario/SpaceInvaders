package com.game.engine.component;

import com.badlogic.gdx.math.Vector2;

public class Facing implements Component {
	private Vector2 direction;
	private Vector2 fromCenter;
	
	public Facing(Vector2 direction, Vector2 fromCenter) {
		super();
		this.direction = direction;
		this.fromCenter = fromCenter;
	}
	public Vector2 getDirection() {
		return direction;
	}
	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}
	public Vector2 getFromCenter() {
		return fromCenter;
	}
	public void setFromCenter(Vector2 fromCenter) {
		this.fromCenter = fromCenter;
	}
}
