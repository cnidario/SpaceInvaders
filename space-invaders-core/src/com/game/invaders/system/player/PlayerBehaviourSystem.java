package com.game.invaders.system.player;

import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.factory.ShootFactory;
import com.badlogic.gdx.math.Vector2;

public class PlayerBehaviourSystem extends AbstractProcess {
	private NodeSet nodeSet;
	private ShootFactory shootFactory;
	
	@SuppressWarnings("unchecked")
	public PlayerBehaviourSystem(EntityNodeSetFactory entityNodeSetFactory, ShootFactory shootFactory) {
		super();
		this.shootFactory = shootFactory; 
		nodeSet = entityNodeSetFactory.create(Motion.class, PlayerShip.class, Position.class);
	}
	private void fire() {
		Node player = nodeSet.one();
		if(player == null) 
			return;
		shootFactory.create(player);
	}
	@Override
	public void update(float dt) {
		Node node = nodeSet.one();
		if(node == null)
			return;
		PlayerShip state_c = (PlayerShip) node.component(PlayerShip.class);
		Motion physics_c = (Motion) node.component(Motion.class);
		Vector2 speed = physics_c.getSpeed();
		
		float shoott = state_c.getShootDelay() - dt;
		if(shoott <= 0) {
			shoott = 0;
		}
		state_c.setShootDelay(shoott);
		
		if(state_c.isFiring() && shoott == 0) {
			fire();
			state_c.setFiring(false);
			state_c.setShootDelay(GameConfigData.PLAYER.SHOOT_DELAY);
		}
		
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
