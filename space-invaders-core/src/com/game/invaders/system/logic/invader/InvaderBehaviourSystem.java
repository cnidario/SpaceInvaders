package com.game.invaders.system.logic.invader;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.invaders.GameConfigData;
import com.game.invaders.GameResources;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.components.PhysicsActorC;
import com.game.invaders.scene.actor.components.PositionActorC;
import com.game.invaders.system.engine.EntityMapper;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.process.AbstractProcess;

public class InvaderBehaviourSystem extends AbstractProcess {
	private EntityManager manager;
	private EntityMapper managedEntities;
	private EventManager eventManager;
	
	public InvaderBehaviourSystem(EntityManager manager, EventManager eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ActorComponentID.GROUP, ActorComponentID.PHYSICS, ActorComponentID.POSITION));
	}
	@Override
	public void update(float dt) {
		for(IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			PhysicsActorC physics_comp = (PhysicsActorC) manager.componentFor(e, ActorComponentID.PHYSICS);
			PositionActorC position_comp = (PositionActorC) manager.componentFor(e, ActorComponentID.POSITION);
			Vector2 speed = physics_comp.getSpeed();
			Vector2 pos = position_comp.getPos();
			if(pos.x > GameConfigData.INVADER.MAXX) {
				speed.x = -speed.x;
				pos.x = pos.x - (pos.x - GameConfigData.INVADER.MAXX);
			} else if(pos.x < GameConfigData.INVADER.MINX) {
				if(speed.x == GameConfigData.INVADER.MAXSPEED || speed.x == -GameConfigData.INVADER.MAXSPEED)
					speed.x = -speed.x;
				else
					speed.x = -speed.x + GameConfigData.INVADER.SPEEDINCREMENT;
				pos.x = pos.x + (GameConfigData.INVADER.MINX - pos.x);
				pos.y -= GameConfigData.INVADER.DESCEND_DISTANCE;
			}
		}
	}
}
