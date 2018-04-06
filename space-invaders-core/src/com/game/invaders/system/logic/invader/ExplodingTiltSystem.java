package com.game.invaders.system.logic.invader;

import java.util.EnumSet;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.scene.actor.component.ExplodingInvaderC;
import com.game.invaders.scene.actor.component.PositionActorC;
import com.game.invaders.system.engine.EntityMapper;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.process.AbstractProcess;

public class ExplodingTiltSystem extends AbstractProcess {
	private EntityManager manager;
	private EntityMapper managedEntities;
	private EventManager eventManager;
	
	public ExplodingTiltSystem(EntityManager manager, EventManager eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ActorComponentID.EXPLODING_INVADER, ActorComponentID.POSITION));
	}
	@Override
	public void update(float dt) {
		for(IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			PositionActorC pos_c = (PositionActorC) manager.componentFor(e, ActorComponentID.POSITION);
			ExplodingInvaderC explo_c = (ExplodingInvaderC) manager.componentFor(e, ActorComponentID.EXPLODING_INVADER);
			float elapsed = explo_c.getElapsed() + dt;
			elapsed %= explo_c.getPeriod();
			explo_c.setElapsed(elapsed);
			elapsed = (float) ((elapsed/explo_c.getPeriod()) * 2 * Math.PI);
			float displacement = (float) Math.sin(elapsed) * explo_c.getTilt();
			pos_c.getPos().x += displacement;
		}
	}
}
