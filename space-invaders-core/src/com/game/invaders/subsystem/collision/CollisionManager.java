package com.game.invaders.subsystem.collision;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.game.invaders.scene.actor.Actor;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.components.CollisionActorC;
import com.game.invaders.subsystem.event.Event;
import com.game.invaders.subsystem.event.EventManager;
import com.game.invaders.subsystem.event.EventManager.EventListener;
import com.game.invaders.subsystem.event.types.ActorLifeCycleEvent;

public class CollisionManager implements EventListener {
	public enum CollisionGroup {
		INVADER,
		PLAYER,
		PLAYER_SHOOT
	}
	public static boolean test(Vector2 aPos, BoundingBox a, Vector2 bPos, BoundingBox b) {
		boolean horizontal_overlap = (aPos.x > bPos.x && aPos.x < bPos.x + b.getWidth()) ||
				(bPos.x > aPos.x && bPos.x < aPos.x + a.getWidth());
		boolean vertical_overlap = (aPos.y > bPos.y && aPos.y < bPos.y + b.getHeight()) ||
				(bPos.y > aPos.y && bPos.y < aPos.y + a.getHeight());
		return horizontal_overlap && vertical_overlap;
	}
	public static class CollisionStrategy {
		private EnumSet<CollisionGroup> collidesWith; //categorías con las que colisiona
		private EnumSet<CollisionGroup> collisionCategories; //categorías a las que pertenece
		
		
		public CollisionStrategy(EnumSet<CollisionGroup> collidesWith, EnumSet<CollisionGroup> collisionCategories) {
			super();
			this.collidesWith = collidesWith;
			this.collisionCategories = collisionCategories;
		}
		public EnumSet<CollisionGroup> getCollisionCategories() {
			return collisionCategories;
		}
		public boolean collides(CollisionEntity a, CollisionEntity b) {
			assert a.collisionStrategy == b.collisionStrategy;
			for(CollisionGroup with : collidesWith) {
				if(b.getCollisionCategories().contains(with)) {
					return test(a.getObj().getPos(), a.getBbox(), b.getObj().getPos(), b.getBbox());
				}
			}
			return false;
		}
	}
	public static class CollisionEntity {
		private Actor obj; //objeto original al que referencia
		private BoundingBox bbox;
		private CollisionStrategy collisionStrategy;
		
		public CollisionEntity(Actor obj, BoundingBox bbox, CollisionStrategy collisionStrategy) {
			super();
			this.obj = obj;
			this.bbox = bbox;
			this.collisionStrategy = collisionStrategy;
		}
		public Actor getObj() {
			return obj;
		}
		public BoundingBox getBbox() {
			return bbox;
		}
		public EnumSet<CollisionGroup> getCollisionCategories() {
			return collisionStrategy.getCollisionCategories();
		}
		public boolean collides(CollisionEntity b) {
			return collisionStrategy.collides(this, b);
		}
	}
	
	public final static CollisionStrategy INVADER_STRATEGY 
				= new CollisionStrategy(EnumSet.noneOf(CollisionGroup.class), EnumSet.of(CollisionGroup.INVADER));
	public final static CollisionStrategy PLAYER_SHOOT_STRATEGY
				= new CollisionStrategy(EnumSet.of(CollisionGroup.INVADER), EnumSet.of(CollisionGroup.PLAYER_SHOOT));
	
	private List<CollisionEntity> entities = new ArrayList<CollisionManager.CollisionEntity>();
	private EventManager event_manager;
	public CollisionManager(EventManager event_manager) {
		this.event_manager = event_manager;
	}
	public void registerEntity(Actor object, BoundingBox bbox, CollisionStrategy st) {
		CollisionEntity ent = new CollisionEntity(object, bbox, st);
		entities.add(ent);
	}
	public void unregisterEntity(CollisionEntity ent) {
		entities.remove(ent);
	}
	public void processCollisions() {
		for(int i = 0; i < entities.size(); i++) {
			CollisionEntity e1 = entities.get(i);
			for(int j = i; j < entities.size(); j++) {
				CollisionEntity e2 = entities.get(i);
				if(e1.collides(e2)) {
					emitCollision(e1, e2);
					break;
				}
			}
		}
	}
	public void emitCollision(CollisionEntity e1, CollisionEntity e2) {
		
	}
	public void init() {
		event_manager.registerHandler(this, EnumSet.of(Event.EventType.ACTOR_CREATED, Event.EventType.ACTOR_DELETED));
	}
	@Override
	public void handle(Event e) {
		if(e.getType() == Event.EventType.ACTOR_CREATED) {
			ActorLifeCycleEvent event = (ActorLifeCycleEvent)e;
			Actor actor = event.getActor();
			CollisionActorC collisionComponent = (CollisionActorC) actor.getComponent(ActorComponentID.COLLISION);
			if(collisionComponent != null)
				registerEntity(actor, collisionComponent.getBoundingBox(), collisionComponent.getCollisionStrategy());
		}///TODO ACTOR_DELETED
	}
}
