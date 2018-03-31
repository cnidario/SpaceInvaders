package com.game.invaders.system.collision;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import com.badlogic.gdx.math.Vector2;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.components.CollisionActorC;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.EventManager.EventListener;
import com.game.invaders.system.event.types.ActorLifeCycleEvent;
import com.game.invaders.system.event.types.CollisionEvent;
import com.game.invaders.system.process.AbstractProcess;

public class CollisionManager extends AbstractProcess implements EventListener {
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
					return test(a.getEntity().getPos(), a.getBbox(), b.getObj().getPos(), b.getBbox());
				}
			}
			return false;
		}
	}
	public static class CollisionEntity {
		private int entity; //objeto original al que referencia
		private BoundingBox bbox;
		private CollisionStrategy collisionStrategy;
		
		public CollisionEntity(int entity, BoundingBox bbox, CollisionStrategy collisionStrategy) {
			super();
			this.entity = entity;
			this.bbox = bbox;
			this.collisionStrategy = collisionStrategy;
		}
		public int getEntity() {
			return entity;
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
	private void registerEntity(int entity, BoundingBox bbox, CollisionStrategy st) {
		CollisionEntity ent = new CollisionEntity(entity, bbox, st);
		entities.add(ent);
	}
	private void unregisterEntity(CollisionEntity ent) {
		entities.remove(ent);
	}
	private void processCollisions() {
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
	private void emitCollision(CollisionEntity e1, CollisionEntity e2) {
		event_manager.queueEvent(new CollisionEvent(e1, e2));
	}
	@Override
	public void handle(Event e) {
		if(e.getType() == Event.EventType.ACTOR_CREATED) {
			ActorLifeCycleEvent event = (ActorLifeCycleEvent)e;
			int entity = event.getEntity();
			CollisionActorC collisionComponent = (CollisionActorC) entity.getComponent(ActorComponentID.COLLISION);
			if(collisionComponent != null)
				registerEntity(entity, collisionComponent.getBoundingBox(), collisionComponent.getCollisionStrategy());
		}///TODO ACTOR_DELETED
	}
	@Override
	public void init() {
		event_manager.registerHandler(this, EnumSet.of(Event.EventType.ACTOR_CREATED, Event.EventType.ACTOR_DELETED));
	}
	@Override
	public void update(float dt) {
		processCollisions();
	}
}
