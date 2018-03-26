package com.game.invaders.scene.actor.components;

import com.game.invaders.scene.actor.Actor;
import com.game.invaders.subsystem.collision.BoundingBox;
import com.game.invaders.subsystem.collision.CollisionManager.CollisionStrategy;

public class CollisionActorC extends AbstractActorComponent {
	private BoundingBox boundingBox;
	private CollisionStrategy collisionStrategy;
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	public CollisionStrategy getCollisionStrategy() {
		return collisionStrategy;
	}
	public void setCollisionStrategy(CollisionStrategy collisionStrategy) {
		this.collisionStrategy = collisionStrategy;
	}
	public CollisionActorC(Actor parent, BoundingBox boundingBox, CollisionStrategy collisionStrategy) {
		super(parent, ActorComponentID.COLLISION);
		this.boundingBox = boundingBox;
		this.collisionStrategy = collisionStrategy;
	}
}
