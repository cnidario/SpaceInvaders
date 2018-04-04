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
import com.game.invaders.scene.actor.component.PlayerShipStateC.PlayerState;
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
		int player = managedEntities.getGroup().first();
		PositionActorC pos_c = (PositionActorC) manager.componentFor(player, ActorComponentID.POSITION);
		manager.addComponent(shoot, new PositionActorC(pos_c.getPos().cpy()));
		manager.addComponent(shoot, new CollisionActorC(GameResources.PLAYER.SHOOT_BBOX, EnumSet.of(CollisionGroup.INVADER), EnumSet.of(CollisionGroup.PLAYER_SHOOT)));
		manager.addComponent(shoot, new RenderableActorC(GameResources.PLAYER.SHOOT));
		manager.addComponent(shoot, new PhysicsActorC(new Vector2(0, GameConfigData.PLAYER.SHOOT_SPEED)));
	}
	@Override
	public void update(float dt) {
		int e = managedEntities.getGroup().first();
		PlayerShipStateC state_c = (PlayerShipStateC) manager.componentFor(e, ActorComponentID.PLAYER_STATE);
		PhysicsActorC physics_c = (PhysicsActorC) manager.componentFor(e, ActorComponentID.PHYSICS);
		Vector2 speed = physics_c.getSpeed();
		
		switch(state_c.getState()) {
			case FIRING:
				speed.x = 0;
				fire();
				state_c.setState(PlayerState.STOPPED);
				break;
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
