package com.game.invaders.scene.actor.component;

import com.badlogic.gdx.math.Vector2;

public class PhysicsActorC extends AbstractActorComponent {
	private Vector2 speed;

	public PhysicsActorC(Vector2 speed) {
		super(ActorComponentID.PHYSICS);
		this.speed = speed;
	}
	public Vector2 getSpeed() {
		return speed;
	}
	public void setSpeed(Vector2 speed) {
		this.speed = speed;
	}
}
