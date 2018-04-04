package com.game.invaders.system.collision;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.component.CollisionActorC;
import com.game.invaders.scene.actor.component.PositionActorC;
import com.game.invaders.scene.actor.component.CollisionActorC.CollisionGroup;
import com.game.invaders.system.engine.EntityMapper;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.types.CollisionEvent;
import com.game.invaders.system.process.AbstractProcess;

public class CollisionManager extends AbstractProcess {
	private EntityMapper managedEntities;
	private EntityManager manager;
	private EventManager eventManager;
	
	public CollisionManager(EntityManager manager, EventManager eventManager) {
		this.manager = manager;
		this.eventManager = eventManager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ActorComponentID.COLLISION, ActorComponentID.POSITION));
	}
	public static boolean test(Vector2 aPos, BoundingBox a, Vector2 bPos, BoundingBox b) {
		boolean horizontal_overlap = (aPos.x > bPos.x && aPos.x < bPos.x + b.getWidth()) ||
				(bPos.x > aPos.x && bPos.x < aPos.x + a.getWidth());
		boolean vertical_overlap = (aPos.y > bPos.y && aPos.y < bPos.y + b.getHeight()) ||
				(bPos.y > aPos.y && bPos.y < aPos.y + a.getHeight());
		return horizontal_overlap && vertical_overlap;
	}
	private static boolean collides(Vector2 p1, Vector2 p2, BoundingBox bb1, BoundingBox bb2, EnumSet<CollisionGroup> cwith, EnumSet<CollisionGroup> ccategories) {
		for(CollisionGroup with : cwith) {
			if(ccategories.contains(with))
				return test(p1, bb1, p2, bb2);
		}
		return false;
	}
	private boolean collides(int e1, int e2) {
		System.out.println("checking collision " + e1 + ", " + e2);
		CollisionActorC col1 = (CollisionActorC) manager.componentFor(e1, ActorComponentID.COLLISION);
		CollisionActorC col2 = (CollisionActorC) manager.componentFor(e2, ActorComponentID.COLLISION);
		PositionActorC p1 = (PositionActorC) manager.componentFor(e1, ActorComponentID.POSITION);
		PositionActorC p2 = (PositionActorC) manager.componentFor(e2, ActorComponentID.POSITION);
		return collides(p1.getPos(), p2.getPos(), col1.getBoundingBox(), col2.getBoundingBox(), col1.getCollidesWith(), col2.getCollisionCategories());
	}
	private void processCollisions() {
		int[] entities = managedEntities.getGroup().iterator().toArray().items;
		for(int i = 0; i < entities.length; i++) {
			int e1 = entities[i];
			for(int j = i; j < entities.length; j++) {
				int e2 = entities[j];
				if(collides(e1, e2)) {
					emitCollision(e1, e2);
					break;
				}
			}
		}
	}
	private void emitCollision(int e1, int e2) {
		eventManager.queueEvent(new CollisionEvent(e1, e2)); //FIXME PROBLEM HERE, borrado inmediato, propagación instantánea evento
	}
	@Override
	public void update(float dt) {
		processCollisions();
	}
}
