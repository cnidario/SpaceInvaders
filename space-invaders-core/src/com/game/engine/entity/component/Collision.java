package com.game.engine.entity.component;

import java.util.EnumSet;

import com.game.engine.entity.AbstractComponent;
import com.game.engine.system.collision.BoundingBox;

public class Collision extends AbstractComponent {
	public enum CollisionGroup {
		INVADER,
		PLAYER,
		PLAYER_SHOOT
	}
	
	private BoundingBox boundingBox;
	private EnumSet<CollisionGroup> collidesWith;
	EnumSet<CollisionGroup> collisionCategories;
	
	public Collision(BoundingBox boundingBox, EnumSet<CollisionGroup> collidesWith,
			EnumSet<CollisionGroup> collisionCategories) {
		super(ComponentID.COLLISION);
		this.boundingBox = boundingBox;
		this.collidesWith = collidesWith;
		this.collisionCategories = collisionCategories;
	}
	public BoundingBox getBoundingBox() {
		return boundingBox;
	}
	public void setBoundingBox(BoundingBox boundingBox) {
		this.boundingBox = boundingBox;
	}
	public EnumSet<CollisionGroup> getCollidesWith() {
		return collidesWith;
	}
	public void setCollidesWith(EnumSet<CollisionGroup> collidesWith) {
		this.collidesWith = collidesWith;
	}
	public EnumSet<CollisionGroup> getCollisionCategories() {
		return collisionCategories;
	}
	public void setCollisionCategories(EnumSet<CollisionGroup> collisionCategories) {
		this.collisionCategories = collisionCategories;
	}
}
