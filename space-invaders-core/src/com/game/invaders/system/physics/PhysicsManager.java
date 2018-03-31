package com.game.invaders.system.physics;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.components.PhysicsActorC;
import com.game.invaders.scene.actor.components.PositionActorC;
import com.game.invaders.system.engine.EntityMapper;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.process.AbstractProcess;

public class PhysicsManager extends AbstractProcess {
	private EventManager eventManager;
	private EntityMapper managedEntities;
	private EntityManager manager;
	
	public PhysicsManager(EntityManager manager, EventManager eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ActorComponentID.PHYSICS, ActorComponentID.POSITION));
	}
	@Override
	public void update(float dt) {
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			PhysicsActorC physics_c = (PhysicsActorC) manager.componentFor(e, ActorComponentID.PHYSICS);
			PositionActorC position_c = (PositionActorC) manager.componentFor(e, ActorComponentID.POSITION);
			Vector2 speed = physics_c.getSpeed();
			position_c.getPos().mulAdd(speed, dt/1000);
		}
	}
}
