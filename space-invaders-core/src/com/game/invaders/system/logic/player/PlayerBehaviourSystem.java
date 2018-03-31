package com.game.invaders.system.logic.player;

import java.util.EnumSet;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.components.PhysicsActorC;
import com.game.invaders.scene.actor.components.PlayerShipStateC;
import com.game.invaders.scene.actor.components.PlayerShipStateC.PlayerState;
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
	@Override
	public void update(float dt) {
		int e = managedEntities.getGroup().first();
		PlayerShipStateC state_c = (PlayerShipStateC) manager.componentFor(e, ActorComponentID.PLAYER_STATE);
		PhysicsActorC physics_c = (PhysicsActorC) manager.componentFor(e, ActorComponentID.PHYSICS);
		Vector2 speed = physics_c.getSpeed();
		
		switch(state_c.getState()) {
			case FIRING:
				speed.x = 0;
				//fire!
				state_c.setState(PlayerState.STOPPED);
				break;
			case MOVING_LEFT:
				speed.x = 100;
				break;
			case MOVING_RIGHT:
				speed.x = -100;
				break;
			case STOPPED:
				speed.x = 0;
				break;
		}
	}
}
