package com.game.engine.system.collision;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.component.Collision;
import com.game.engine.entity.component.Position;
import com.game.engine.factory.EntityBuilderFactory;
import com.game.engine.system.entity.node.EntityNodeSetFactory;
import com.game.engine.system.entity.node.Node;
import com.game.engine.system.entity.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;

public class CollisionSystem<E extends Enum<E>> extends AbstractProcess {
	private NodeSet nodeSet;
	private EntityManager manager;
	private EntityBuilderFactory entityBuilderFactory;
	
	@SuppressWarnings("unchecked")
	public CollisionSystem(EntityManager manager, EntityBuilderFactory entityBuilderFactory, EntityNodeSetFactory entityNodeSetFactory) {
		this.manager = manager;
		this.entityBuilderFactory = entityBuilderFactory;
		nodeSet = entityNodeSetFactory.create(Collision.class, Position.class);
	}
	public static boolean test(Vector2 aPos, BoundingBox a, Vector2 bPos, BoundingBox b) {
		boolean horizontal_overlap = (aPos.x > bPos.x && aPos.x < bPos.x + b.getWidth()) ||
				(bPos.x > aPos.x && bPos.x < aPos.x + a.getWidth());
		boolean vertical_overlap = (aPos.y > bPos.y && aPos.y < bPos.y + b.getHeight()) ||
				(bPos.y > aPos.y && bPos.y < aPos.y + a.getHeight());
		return horizontal_overlap && vertical_overlap;
	}
	@SuppressWarnings("unchecked")
	private void checkCollides(int e1, int e2) {
		Collision<E> col1 = (Collision<E>) manager.componentFor(e1, Collision.class);
		Collision<E> col2 = (Collision<E>) manager.componentFor(e2, Collision.class);
		Position p1 = (Position) manager.componentFor(e1, Position.class);
		Position p2 = (Position) manager.componentFor(e2, Position.class);
		EnumSet<E> cwith1 = col1.getCollidesWith().clone();
		EnumSet<E> ccats2 = col2.getCollisionCategories();
		cwith1.retainAll(ccats2);
		if(!cwith1.isEmpty())
			if(test(p1.getPos(), col1.getBoundingBox(), p2.getPos(), col2.getBoundingBox())) {
				emitCollision(e1, e2, p1.getPos());
				return;
			}
		EnumSet<E> cwith2 = col2.getCollidesWith().clone();
		EnumSet<E> ccats1 = col1.getCollisionCategories();
		cwith2.retainAll(ccats1);
		if(!cwith2.isEmpty())
			if(test(p2.getPos(), col2.getBoundingBox(), p1.getPos(), col1.getBoundingBox())) {
				emitCollision(e2, e1, p2.getPos());
				return;
			}
	}
	private void processCollisions() {
		for (Node node : nodeSet) {
			//FIXME
		}
		int[] entities = managedEntities.getGroup().iterator().toArray().items;
		for(int i = 0; i < entities.length; i++) {
			int e1 = entities[i];
			for(int j = i; j < entities.length; j++) {
				int e2 = entities[j];
				checkCollides(e1, e2);
			}
		}
	}
	private void emitCollision(int e1, int e2, Vector2 pos) {
		entityBuilderFactory.create()
			.position(pos.cpy())
			.impact(e1, e2)
			.build();
	}
	@Override
	public void update(float dt) {
		processCollisions();
	}
}
