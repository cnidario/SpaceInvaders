package com.game.engine.system.controller;

import com.game.engine.entity.component.ShipAction;
import com.game.engine.entity.component.UserControlled;
import com.game.engine.system.entity.node.EntityNodeSetFactory;
import com.game.engine.system.entity.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.component.PlayerShip.PlayerState;

public class ControllerSystem extends AbstractProcess {
	private NodeSet controlledNodeSet;
	private NodeSet inputNodeSet;
	
	@SuppressWarnings("unchecked")
	public ControllerSystem(EntityNodeSetFactory entityNodeSetFactory) {
		controlledNodeSet = entityNodeSetFactory.create(UserControlled.class, PlayerShip.class);
		inputNodeSet = entityNodeSetFactory.create(ShipAction.class);
	}
	private void handle() {
		PlayerShip state_c = (PlayerShip) controlledNodeSet.iterator().next().component(PlayerShip.class);
		ShipAction shipAction = (ShipAction) inputNodeSet.iterator().next().component(ShipAction.class);
		
		if(shipAction.isMoveLeft()) {
			state_c.setState(PlayerState.MOVING_LEFT);
		} else if(shipAction.isMoveRight()) {
			state_c.setState(PlayerState.MOVING_RIGHT);
		} else {
			state_c.setState(PlayerState.STOPPED);
		}
		if(shipAction.isShoot()) {
			state_c.setFiring(true);
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
