package com.game.invaders.system.logic.invader;

import java.util.EnumSet;
import java.util.Random;

import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.component.InvaderStateC;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.invaders.GameResources;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.system.engine.EntityMapper;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.process.AbstractProcess;

public class InvaderStateSystem extends AbstractProcess {
	private EntityManager manager;
	private EntityMapper managedEntities;
	private EventManager eventManager;
	
	public InvaderStateSystem(EntityManager manager, EventManager eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ActorComponentID.INVADER_STATE));
	}
	@Override
	public void update(float dt) {
		for(IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			InvaderStateC state_c = (InvaderStateC) manager.componentFor(e, ActorComponentID.INVADER_STATE);
			switch(state_c.getStateID()) {
				case ALIVE:
					break;
				case DYING:
					float dtime = state_c.getDyingTime() - dt;
					state_c.setDyingTime(dtime);
					if(dtime <= 0) {
						manager.markEntityForRemove(e);
						Sound sound = GameResources.GAME.EXPLOSIONS[new Random().nextInt(GameResources.GAME.EXPLOSIONS.length)];
						sound.play(.3f);
					}
					break;
			}
		}
	}
}
