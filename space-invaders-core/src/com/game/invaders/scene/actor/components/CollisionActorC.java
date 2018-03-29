package com.game.invaders.scene.actor.components;

import com.game.invaders.system.collision.BoundingBox;
import com.game.invaders.system.collision.CollisionManager.CollisionStrategy;

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
	public CollisionActorC(BoundingBox boundingBox, CollisionStrategy collisionStrategy) {
		super(ActorComponentID.COLLISION);
		this.boundingBox = boundingBox;
		this.collisionStrategy = collisionStrategy;
	}
}
