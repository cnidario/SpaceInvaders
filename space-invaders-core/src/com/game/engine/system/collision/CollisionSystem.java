package com.game.engine.system.collision;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.component.Collision;
import com.game.engine.entity.component.Position;
import com.game.engine.system.EntityMapper;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.types.CollisionEvent;
import com.game.engine.system.process.AbstractProcess;

public class CollisionSystem<E extends Enum<E>> extends AbstractProcess {
	private EntityMapper managedEntities;
	private EntityManager manager;
	private EventSystem eventManager;
	
	public CollisionSystem(EntityManager manager, EventSystem eventManager) {
		this.manager = manager;
		this.eventManager = eventManager;
		Set<Class<? extends Component>> cs = new HashSet<Class<? extends Component>>();
		cs.add(Collision.class);
		cs.add(Position.class);
		managedEntities = new EntityMapper(manager, eventManager, cs);
	}
	public static boolean test(Vector2 aPos, BoundingBox a, Vector2 bPos, BoundingBox b) {
		boolean horizontal_overlap = (aPos.x > bPos.x && aPos.x < bPos.x + b.getWidth()) ||
				(bPos.x > aPos.x && bPos.x < aPos.x + a.getWidth());
		boolean vertical_overlap = (aPos.y > bPos.y && aPos.y < bPos.y + b.getHeight()) ||
				(bPos.y > aPos.y && bPos.y < aPos.y + a.getHeight());
		return horizontal_overlap && vertical_overlap;
	}
	private enum CollisionType {
		COLLISION,
		REVERSE_COLLISION,
		NO_COLLISION
	}
	@SuppressWarnings("unchecked")
	private CollisionType collides(int e1, int e2) {
		Collision<E> col1 = (Collision<E>) manager.componentFor(e1, Collision.class);
		Collision<E> col2 = (Collision<E>) manager.componentFor(e2, Collision.class);
		Position p1 = (Position) manager.componentFor(e1, Position.class);
		Position p2 = (Position) manager.componentFor(e2, Position.class);
		EnumSet<E> cwith1 = col1.getCollidesWith().clone();
		EnumSet<E> ccats2 = col2.getCollisionCategories();
		cwith1.retainAll(ccats2);
		if(!cwith1.isEmpty())
			if(test(p1.getPos(), col1.getBoundingBox(), p2.getPos(), col2.getBoundingBox()))
				return CollisionType.COLLISION;
		EnumSet<E> cwith2 = col2.getCollidesWith().clone();
		EnumSet<E> ccats1 = col1.getCollisionCategories();
		cwith2.retainAll(ccats1);
		if(!cwith2.isEmpty())
			if(test(p2.getPos(), col2.getBoundingBox(), p1.getPos(), col1.getBoundingBox()))
				return CollisionType.REVERSE_COLLISION;
		return CollisionType.NO_COLLISION;
	}
	private void processCollisions() {
		int[] entities = managedEntities.getGroup().iterator().toArray().items;
		for(int i = 0; i < entities.length; i++) {
			int e1 = entities[i];
			for(int j = i; j < entities.length; j++) {
				int e2 = entities[j];
				switch(collides(e1, e2)) {
				case COLLISION:
					emitCollision(e1, e2);
					break;
				case REVERSE_COLLISION:
					emitCollision(e2, e1);
					break;
				case NO_COLLISION:
					break;
				}
			}
		}
	}
	private void emitCollision(int e1, int e2) {
		eventManager.queueEvent(new CollisionEvent(e1, e2));
	}
	@Override
	public void update(float dt) {
		processCollisions();
	}
}
