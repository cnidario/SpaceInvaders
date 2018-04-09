package com.game.invaders.system.invader;

import java.util.EnumSet;

import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.Component.ComponentID;
import com.game.engine.entity.component.Position;
import com.game.engine.system.EntityMapper;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.TiltExploding;

public class ExplodingTiltSystem extends AbstractProcess {
	private EntityManager manager;
	private EntityMapper managedEntities;
	private EventSystem eventManager;

	public ExplodingTiltSystem(EntityManager manager, EventSystem eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		managedEntities = new EntityMapper(manager, eventManager,
				EnumSet.of(ComponentID.TILT_EXPLODING, ComponentID.POSITION));
	}

	@Override
	public void update(float dt) {
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext;) {
			int e = iter.next();
			Position pos_c = (Position) manager.componentFor(e, ComponentID.POSITION);
			TiltExploding explo_c = (TiltExploding) manager.componentFor(e, ComponentID.TILT_EXPLODING);
			float elapsed = explo_c.getElapsed() + dt;
			elapsed %= explo_c.getPeriod();
			explo_c.setElapsed(elapsed);
			elapsed = (float) ((elapsed / explo_c.getPeriod()) * 2 * Math.PI);
			float displacement = (float) Math.sin(elapsed) * explo_c.getTilt();
			pos_c.getPos().x += displacement;
		}
	}
}
