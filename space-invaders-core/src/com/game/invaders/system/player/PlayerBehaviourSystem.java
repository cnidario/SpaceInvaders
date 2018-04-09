package com.game.invaders.system.player;

import java.util.EnumSet;
import java.util.Random;

import com.game.engine.entity.EntityManager;
import com.game.engine.entity.Component.ComponentID;
import com.game.engine.entity.component.Collision;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.Renderable;
import com.game.engine.entity.component.Collision.CollisionGroup;
import com.game.engine.system.EntityMapper;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

public class PlayerBehaviourSystem extends AbstractProcess {
	private EventSystem eventManager;
	private EntityMapper managedEntities;
	private EntityManager manager;
	
	public PlayerBehaviourSystem(EntityManager manager, EventSystem eventManager) {
		super();
		this.eventManager = eventManager;
		this.manager = manager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ComponentID.PLAYER_SHIP, ComponentID.MOTION)); 
	}
	private void fire() {
		int shoot = manager.createEntity();
		int player = managedEntities.one();
		if(player == -1) 
			return;
		Position pos_c = (Position) manager.componentFor(player, ComponentID.POSITION);
		Vector2 shootp = pos_c.getPos().cpy();
		shootp.x += GameResources.PLAYER.IMAGE.getWidth() / 2 - GameResources.PLAYER.SHOOT_IMG.getWidth() / 2;
		manager.addComponent(shoot, new Position(shootp));
		manager.addComponent(shoot, new Collision(GameResources.PLAYER.SHOOT_BBOX, EnumSet.of(CollisionGroup.INVADER), EnumSet.of(CollisionGroup.PLAYER_SHOOT)));
		manager.addComponent(shoot, new Renderable(GameResources.PLAYER.SHOOT));
		manager.addComponent(shoot, new Motion(new Vector2(0, GameConfigData.PLAYER.SHOOT_SPEED)));
		Sound sound = GameResources.GAME.SHOOTS[new Random().nextInt(GameResources.GAME.SHOOTS.length)];
		sound.play(1f);
	}
	@Override
	public void update(float dt) {
		int e = managedEntities.one();
		if(e == -1)
			return;
		PlayerShip state_c = (PlayerShip) manager.componentFor(e, ComponentID.PLAYER_SHIP);
		Motion physics_c = (Motion) manager.componentFor(e, ComponentID.MOTION);
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
