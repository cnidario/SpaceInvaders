package com.game.invaders.system.player;

import com.game.engine.component.Facing;
import com.game.engine.component.Position;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.FiringBehaviour;
import com.game.invaders.factory.ShootFactory;

public class FiringBehaviourSystem extends AbstractProcess {
	private NodeSet nodeSet;
	private ShootFactory shootFactory;
	
	@SuppressWarnings("unchecked")
	public FiringBehaviourSystem(EntityNodeSetFactory entityNodeSetFactory, ShootFactory shootFactory) {
		super();
		this.shootFactory = shootFactory; 
		nodeSet = entityNodeSetFactory.create(FiringBehaviour.class, Position.class, Facing.class);
	}
	@Override
	public void update(float dt) {
		for (Node node : nodeSet) {
			FiringBehaviour firing_c = (FiringBehaviour) node.component(FiringBehaviour.class);
			Position pos_c = (Position) node.component(Position.class);
			Facing facing_c = (Facing) node.component(Facing.class);
			if(firing_c.isFiring()) {
				firing_c.setFiring(false);
				float shootTimeLeft = firing_c.getShootTimeLeft() - dt;
				if(shootTimeLeft <= 0) {
					shootTimeLeft = 0;
				}
				firing_c.setShootTimeLeft(shootTimeLeft);
				if(shootTimeLeft == 0) {
					shootFactory.create(node,
							pos_c.getPos().cpy().add(facing_c.getFromCenter()),
							facing_c.getDirection().cpy());
					firing_c.setShootTimeLeft(firing_c.getShootDelay());
				}
			}
		}
	}
}
