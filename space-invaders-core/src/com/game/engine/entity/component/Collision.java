package com.game.engine.entity.component;

import java.util.EnumSet;
import com.game.engine.entity.Component;
import com.game.engine.system.collision.BoundingBox;

public class Collision<T extends Enum<T>> implements Component {
	private BoundingBox boundingBox;
	private EnumSet<T> collidesWith;
	EnumSet<T> collisionCategories;
	
	public Collision(BoundingBox boundingBox, EnumSet<T> collidesWith,
			EnumSet<T> collisionCategories) {
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
	public EnumSet<T> getCollidesWith() {
		return collidesWith;
	}
	public void setCollidesWith(EnumSet<T> collidesWith) {
		this.collidesWith = collidesWith;
	}
	public EnumSet<T> getCollisionCategories() {
		return collisionCategories;
	}
	public void setCollisionCategories(EnumSet<T> collisionCategories) {
		this.collisionCategories = collisionCategories;
	}
}
