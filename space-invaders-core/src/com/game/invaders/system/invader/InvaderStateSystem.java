package com.game.invaders.system.invader;

import java.util.HashSet;
import java.util.Set;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.EntityMapper;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.Invader;
import com.game.invaders.factory.InvaderDestroyedEventFactory;

public class InvaderStateSystem extends AbstractProcess {
	private EntityManager manager;
	private EntityMapper managedEntities;
	private EventSystem eventManager;
	private InvaderDestroyedEventFactory invaderDestroyedFactory;

	public InvaderStateSystem(EntityManager manager, EventSystem eventManager,InvaderDestroyedEventFactory invaderDestroyedFactory) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		this.invaderDestroyedFactory = invaderDestroyedFactory;
		Set<Class<? extends Component>> cs = new HashSet<Class<? extends Component>>();
		cs.add(Invader.class);
		managedEntities = new EntityMapper(manager, eventManager, cs);
	}

	@Override
	public void update(float dt) {
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext;) {
			int e = iter.next();
			Invader state_c = (Invader) manager.componentFor(e, Invader.class);
			switch (state_c.getStateID()) {
			case ALIVE:
				break;
			case DYING:
				float dtime = state_c.getDyingTime() - dt;
				state_c.setDyingTime(dtime);
				if (dtime <= 0) {
					manager.markEntityForRemove(e);
					invaderDestroyedFactory.create(e);
				}
				break;
			}
		}
	}
}
