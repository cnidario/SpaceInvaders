package com.game.invaders.system.logic.player;

import java.util.EnumSet;

import com.game.invaders.GameConfigData;
import com.game.invaders.GameResources;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.component.CollisionActorC;
import com.game.invaders.scene.actor.component.PhysicsActorC;
import com.game.invaders.scene.actor.component.PlayerShipStateC;
import com.game.invaders.scene.actor.component.PositionActorC;
import com.game.invaders.scene.actor.component.RenderableActorC;
import com.game.invaders.scene.actor.component.CollisionActorC.CollisionGroup;
import com.badlogic.gdx.math.Vector2;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.system.engine.EntityMapper;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.process.AbstractProcess;

public class PlayerBehaviourSystem extends AbstractProcess {
	private EventManager eventManager;
	private EntityMapper managedEntities;
	private EntityManager manager;
	
	public PlayerBehaviourSystem(EntityManager manager, EventManager eventManager) {
		super();
		this.eventManager = eventManager;
		this.manager = manager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ActorComponentID.PLAYER_STATE, ActorComponentID.PHYSICS)); 
	}
	private void fire() {
		int shoot = manager.createEntity();
		int player = managedEntities.one();
		if(player == -1) 
			return;
		PositionActorC pos_c = (PositionActorC) manager.componentFor(player, ActorComponentID.POSITION);
		Vector2 shootp = pos_c.getPos().cpy();
		shootp.x += GameResources.PLAYER.IMAGE.getWidth() / 2 - GameResources.PLAYER.SHOOT_IMG.getWidth() / 2;
		manager.addComponent(shoot, new PositionActorC(shootp));
		manager.addComponent(shoot, new CollisionActorC(GameResources.PLAYER.SHOOT_BBOX, EnumSet.of(CollisionGroup.INVADER), EnumSet.of(CollisionGroup.PLAYER_SHOOT)));
		manager.addComponent(shoot, new RenderableActorC(GameResources.PLAYER.SHOOT));
		manager.addComponent(shoot, new PhysicsActorC(new Vector2(0, GameConfigData.PLAYER.SHOOT_SPEED)));
	}
	@Override
	public void update(float dt) {
		int e = managedEntities.one();
		if(e == -1)
			return;
		PlayerShipStateC state_c = (PlayerShipStateC) manager.componentFor(e, ActorComponentID.PLAYER_STATE);
		PhysicsActorC physics_c = (PhysicsActorC) manager.componentFor(e, ActorComponentID.PHYSICS);
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
