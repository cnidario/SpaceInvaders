package com.game.invaders.scene.actor.components;

import com.badlogic.gdx.math.Vector2;

public class PositionActorC extends AbstractActorComponent {
	private Vector2 pos;

	public PositionActorC() {
		super(ActorComponentID.POSITION);
	}
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
}
