package com.game.invaders.factory;

import com.game.engine.component.Position;
import com.game.engine.node.Node;
import com.game.invaders.component.InvaderImpact;

public class InvaderImpactFactory {
	public InvaderImpactFactory() {
	}
	public Node create(Node invader, float time) {
		Node impact = invader.create(
				new Position(((Position)invader.component(Position.class)).getPos().cpy()),
				new InvaderImpact(invader.getId(), time)
				);
		return impact;
	}
}
