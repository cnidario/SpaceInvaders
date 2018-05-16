package com.game.engine.system.motion;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.component.Motion;
import com.game.engine.component.Position;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;

public class MotionSystem extends AbstractProcess {
	private NodeSet nodeSet;
	
	@SuppressWarnings("unchecked")
	public MotionSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		nodeSet = entityNodeSetFactory.create(Motion.class, Position.class);
	}
	@Override
	public void update(float dt) {
		for (Node node : nodeSet) {
			Motion physics_c = (Motion) node.component(Motion.class);
			Position position_c = (Position) node.component(Position.class);
			Vector2 speed = physics_c.getSpeed();
			position_c.getPos().mulAdd(speed, dt/1000);
		}
	}
}
