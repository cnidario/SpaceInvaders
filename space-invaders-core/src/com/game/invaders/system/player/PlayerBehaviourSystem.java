package com.game.invaders.system.player;

import com.game.engine.component.Motion;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.data.GameConfigData;
import com.badlogic.gdx.math.Vector2;

public class PlayerBehaviourSystem extends AbstractProcess {
	private NodeSet nodeSet;
	
	@SuppressWarnings("unchecked")
	public PlayerBehaviourSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		nodeSet = entityNodeSetFactory.create(Motion.class, PlayerShip.class);
	}
	@Override
	public void update(float dt) {
		Node node = nodeSet.one();
		if(node == null)
			return;
		PlayerShip state_c = (PlayerShip) node.component(PlayerShip.class);
		Motion physics_c = (Motion) node.component(Motion.class);
		Vector2 speed = physics_c.getSpeed();
		
		switch(state_c.getState()) {
			case MOVING_LEFT:
				speed.x = -GameConfigData.PLAYER.MOVEMENT_SPEED;
				break;
			case MOVING_RIGHT:
				speed.x = GameConfigData.PLAYER.MOVEMENT_SPEED;
				break;
			case STOPPED:
				speed.x = 0;
				break;
		}
	}
}
