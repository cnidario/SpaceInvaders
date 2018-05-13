package com.game.engine.system.collision;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.component.Collision;
import com.game.engine.entity.component.Impact;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.ShortLife;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;

public class CollisionSystem<E extends Enum<E>> extends AbstractProcess {
	private NodeSet nodeSet;
	
	@SuppressWarnings("unchecked")
	public CollisionSystem(EntityNodeSetFactory entityNodeSetFactory) {
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
	private void checkCollides(Node e1, Node e2) {
		Collision<E> col1 = (Collision<E>) e1.component(Collision.class);
		Collision<E> col2 = (Collision<E>) e2.component(Collision.class);
		Position p1 = (Position) e1.component(Position.class);
		Position p2 = (Position) e2.component(Position.class);
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
		Iterator<Node> i = nodeSet.iterator();
		Set<Node> processed = new HashSet<Node>();
		while(i.hasNext()) {
			Node ni = i.next();
			if(!processed.contains(ni)) {
				for (Node nj : processed) {
					checkCollides(nj, ni);
				}
				processed.add(ni);
			}
		}
	}
	private void emitCollision(Node e1, Node e2, Vector2 pos) {
		e1.create(
				new Position(pos.cpy()),
				new Impact(e1.getId(), e2.getId()),
				new ShortLife()
				);
	}
	@Override
	public void update(float dt) {
		processCollisions();
	}
}
