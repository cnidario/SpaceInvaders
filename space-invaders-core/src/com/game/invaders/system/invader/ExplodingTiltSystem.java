package com.game.invaders.system.invader;

import java.util.HashSet;
import java.util.Set;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
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
		Set<Class<? extends Component>> cs = new HashSet<Class<? extends Component>>();
		cs.add(TiltExploding.class);
		cs.add(Position.class);
		managedEntities = new EntityMapper(manager, eventManager, cs);
	}

	@Override
	public void update(float dt) {
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext;) {
			int e = iter.next();
			Position pos_c = (Position) manager.componentFor(e, Position.class);
			TiltExploding explo_c = (TiltExploding) manager.componentFor(e, TiltExploding.class);
			float elapsed = explo_c.getElapsed() + dt;
			elapsed %= explo_c.getPeriod();
			explo_c.setElapsed(elapsed);
			elapsed = (float) ((elapsed / explo_c.getPeriod()) * 2 * Math.PI);
			float displacement = (float) Math.sin(elapsed) * explo_c.getTilt();
			pos_c.getPos().x += displacement;
		}
	}
}
