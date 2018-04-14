package com.game.invaders.system.player;

import java.util.HashSet;
import java.util.Set;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.system.EntityMapper;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;
import com.game.invaders.factory.ShootFactory;
import com.badlogic.gdx.math.Vector2;

public class PlayerBehaviourSystem extends AbstractProcess {
	private EventSystem eventManager;
	private EntityMapper managedEntities;
	private EntityManager manager;
	private ShootFactory shootFactory;
	
	public PlayerBehaviourSystem(EntityManager manager, EventSystem eventManager, ShootFactory shootFactory) {
		super();
		this.eventManager = eventManager;
		this.manager = manager;
		this.shootFactory = shootFactory;
		
		Set<Class<? extends Component>> cs = new HashSet<Class<? extends Component>>();
		cs.add(Motion.class);
		cs.add(PlayerShip.class);
		cs.add(Position.class);
		managedEntities = new EntityMapper(manager, eventManager, cs); 
	}
	private void fire() {
		int player = managedEntities.one();
		if(player == -1) 
			return;
		Position pos_c = (Position) manager.componentFor(player, Position.class);
		Vector2 shootp = pos_c.getPos().cpy();
		shootp.x += GameResources.PLAYER.IMAGE.getWidth() / 2 - GameResources.PLAYER.SHOOT_IMG.getWidth() / 2;
		shootFactory.create(shootp);
	}
	@Override
	public void update(float dt) {
		int e = managedEntities.one();
		if(e == -1)
			return;
		PlayerShip state_c = (PlayerShip) manager.componentFor(e, PlayerShip.class);
		Motion physics_c = (Motion) manager.componentFor(e, Motion.class);
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
