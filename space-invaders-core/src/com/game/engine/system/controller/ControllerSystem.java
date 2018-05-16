package com.game.engine.system.controller;

import com.game.engine.component.ShipAction;
import com.game.engine.component.UserControlled;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.FiringBehaviour;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.component.PlayerShip.PlayerState;

public class ControllerSystem extends AbstractProcess {
	private NodeSet controlledNodeSet;
	private NodeSet inputNodeSet;
	
	@SuppressWarnings("unchecked")
	public ControllerSystem(EntityNodeSetFactory entityNodeSetFactory) {
		controlledNodeSet = entityNodeSetFactory.create(UserControlled.class, PlayerShip.class, FiringBehaviour.class);
		inputNodeSet = entityNodeSetFactory.create(ShipAction.class);
	}
	private void handle() {
		Node controlled = controlledNodeSet.one();
		Node input = inputNodeSet.one();
		ShipAction shipAction = (ShipAction) input.component(ShipAction.class);
		PlayerShip state_c = (PlayerShip) controlled.component(PlayerShip.class);
		FiringBehaviour firing_c = (FiringBehaviour) controlled.component(FiringBehaviour.class);
		
		if(shipAction.isMoveLeft()) {
			state_c.setState(PlayerState.MOVING_LEFT);
		} else if(shipAction.isMoveRight()) {
			state_c.setState(PlayerState.MOVING_RIGHT);
		} else {
			state_c.setState(PlayerState.STOPPED);
		}
		if(shipAction.isShoot()) {
			firing_c.setFiring(true);
		}
	}
	@Override
	public void init() {
	}
	@Override
	public void update(float dt) {
		handle();
	}
}
