package com.game.engine.component;

import com.badlogic.gdx.math.Vector2;

public class Motion implements Component {
	private Vector2 speed;

	public Motion(Vector2 speed) {
		this.speed = speed;
	}
	public Vector2 getSpeed() {
		return speed;
	}
	public void setSpeed(Vector2 speed) {
		this.speed = speed;
	}
}
