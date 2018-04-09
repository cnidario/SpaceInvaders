package com.game.invaders.system;

import java.util.EnumSet;
import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.Component.ComponentID;
import com.game.engine.entity.component.Animation;
import com.game.engine.system.event.Event;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.Event.EventType;
import com.game.engine.system.event.EventSystem.EventListener;
import com.game.engine.system.event.types.CollisionEvent;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.Invader;
import com.game.invaders.component.TiltExploding;
import com.game.invaders.component.Invader.InvaderStateID;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;

public class ShootImpactManager extends AbstractProcess {
	private EntityManager manager;
	private EventSystem eventManager;
	
	public ShootImpactManager(EntityManager manager, EventSystem eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
	}
	@Override
	public void init() {
		eventManager.registerHandler(new EventListener() {
			@Override
			public void handle(Event e) {
				CollisionEvent ev = (CollisionEvent) e;
				int e1 = ev.getEntity1();
				int e2 = ev.getEntity2();
				Invader invader_c = (Invader) manager.componentFor(e1, ComponentID.INVADER);
				int invader = e1, shoot = e2;
				if(invader_c == null) {
					invader_c = (Invader) manager.componentFor(e2, ComponentID.INVADER);
					invader = e2;
					shoot = e1;
				}
				invader_c.setStateID(InvaderStateID.DYING);
				invader_c.setDyingTime(GameConfigData.INVADER.EXPLOSION_DELAY);
				TiltExploding explo_c = new TiltExploding(6, GameConfigData.INVADER.EXPLOSION_DELAY/5);
				manager.addComponent(invader, explo_c);
				Animation anim_c = (Animation) manager.componentFor(invader, ComponentID.ANIMATION);
				if(anim_c != null)
					manager.removeComponent(invader, anim_c);
				//manager.addComponent(invader, new AnimationSpriteC(GameResources.INVADER.EXPLOSION, GameConfigData.INVADER.EXPLOSION_DELAY));
				manager.markEntityForRemove(shoot);
				Sound sound = GameResources.GAME.HITS[new Random().nextInt(GameResources.GAME.HITS.length)];
				sound.play(1f);
			}
		}, EnumSet.of(EventType.COLLISION));
	}
	@Override
	public void update(float dt) {
		
	}
}