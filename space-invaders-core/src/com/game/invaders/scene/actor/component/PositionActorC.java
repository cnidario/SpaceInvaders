package com.game.invaders.scene.actor.component;

import com.badlogic.gdx.math.Vector2;

public class PositionActorC extends AbstractActorComponent {
	private Vector2 pos;

	public PositionActorC(Vector2 pos) {
		super(ActorComponentID.POSITION);
		this.pos = pos;
	}
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
}