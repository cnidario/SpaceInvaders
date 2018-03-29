package com.game.invaders.scene.actor.components;

import com.badlogic.gdx.math.Vector2;

public class PhysicsActorC extends AbstractActorComponent {
	private Vector2 speed;

	public PhysicsActorC() {
		super(ActorComponentID.PHYSICS);
		speed = new Vector2();
	}
	public Vector2 getSpeed() {
		return speed;
	}
	public void setSpeed(Vector2 speed) {
		this.speed = speed;
	}
}
