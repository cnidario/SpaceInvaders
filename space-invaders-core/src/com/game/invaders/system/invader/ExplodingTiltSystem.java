package com.game.invaders.system.invader;

import com.game.engine.entity.component.Position;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.TiltExploding;

public class ExplodingTiltSystem extends AbstractProcess {
	private NodeSet nodeSet;

	@SuppressWarnings("unchecked")
	public ExplodingTiltSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		nodeSet = entityNodeSetFactory.create(TiltExploding.class, Position.class);
	}

	@Override
	public void update(float dt) {
		for (Node node : nodeSet) {
			Position pos_c = (Position) node.component(Position.class);
			TiltExploding explo_c = (TiltExploding) node.component(TiltExploding.class);
			float elapsed = explo_c.getElapsed() + dt;
			elapsed %= explo_c.getPeriod();
			explo_c.setElapsed(elapsed);
			elapsed = (float) ((elapsed / explo_c.getPeriod()) * 2 * Math.PI);
			float displacement = (float) Math.sin(elapsed) * explo_c.getTilt();
			pos_c.getPos().x += displacement;
		}
	}
}
