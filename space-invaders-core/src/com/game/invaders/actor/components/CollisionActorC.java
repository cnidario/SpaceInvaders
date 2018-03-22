package com.game.invaders.actor.components;

import com.game.invaders.actor.ActorComponent;
import com.game.invaders.subsystem.collision.BoundingBox;
import com.game.invaders.subsystem.collision.CollisionManager.CollisionStrategy;

public class CollisionActorC implements ActorComponent {
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
		super();
		this.boundingBox = boundingBox;
		this.collisionStrategy = collisionStrategy;
	}
	@Override
	public ActorComponentID getID() { return ActorComponentID.COLLISION; }

}
