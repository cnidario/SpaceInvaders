package com.game.invaders.system.invader;

import com.game.engine.component.Destroyed;
import com.game.engine.component.ShortLife;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.node.Node;
import com.game.engine.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.Invader;
import com.game.invaders.component.InvaderExplosion;

public class InvaderStateSystem extends AbstractProcess {
	private NodeSet nodeSet;

	@SuppressWarnings("unchecked")
	public InvaderStateSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		nodeSet = entityNodeSetFactory.create(Invader.class);
	}

	@Override
	public void update(float dt) {
		for (Node node : nodeSet) {
			Invader state_c = (Invader) node.component(Invader.class);
			switch (state_c.getStateID()) {
			case ALIVE:
				break;
			case DYING:
				float dtime = state_c.getDyingTime() - dt;
				state_c.setDyingTime(dtime);
				if (dtime <= 0) {
					node.create(new InvaderExplosion(), new ShortLife());
					node.add(new Destroyed());
				}
				break;
			}
		}
	}
}
