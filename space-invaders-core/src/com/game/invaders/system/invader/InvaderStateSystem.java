package com.game.invaders.system.invader;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.EntityMapper;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.Invader;
import com.game.invaders.data.GameResources;

public class InvaderStateSystem extends AbstractProcess {
	private EntityManager manager;
	private EntityMapper managedEntities;
	private EventSystem eventManager;

	public InvaderStateSystem(EntityManager manager, EventSystem eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
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
					Sound sound = GameResources.GAME.EXPLOSIONS[new Random()
							.nextInt(GameResources.GAME.EXPLOSIONS.length)];
					sound.play(.3f);
				}
				break;
			}
		}
	}
}
