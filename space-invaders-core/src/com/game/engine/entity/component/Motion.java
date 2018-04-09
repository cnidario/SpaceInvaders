package com.game.engine.entity.component;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.AbstractComponent;

public class Motion extends AbstractComponent {
	private Vector2 speed;

	public Motion(Vector2 speed) {
		super(ComponentID.MOTION);
		this.speed = speed;
	}
	public Vector2 getSpeed() {
		return speed;
	}
	public void setSpeed(Vector2 speed) {
		this.speed = speed;
	}
}
