package com.game.invaders.system.logic;

import java.util.EnumSet;

import com.game.invaders.GameConfigData;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.component.AnimationSpriteC;
import com.game.invaders.scene.actor.component.ExplodingInvaderC;
import com.game.invaders.scene.actor.component.InvaderStateC;
import com.game.invaders.scene.actor.component.InvaderStateC.InvaderStateID;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.Event.EventType;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.EventManager.EventListener;
import com.game.invaders.system.event.types.CollisionEvent;
import com.game.invaders.system.process.AbstractProcess;

public class ShootImpactManager extends AbstractProcess {
	private EntityManager manager;
	private EventManager eventManager;
	
	public ShootImpactManager(EntityManager manager, EventManager eventManager) {
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
				InvaderStateC invader_c = (InvaderStateC) manager.componentFor(e1, ActorComponentID.INVADER_STATE);
				int invader = e1, shoot = e2;
				if(invader_c == null) {
					invader_c = (InvaderStateC) manager.componentFor(e2, ActorComponentID.INVADER_STATE);
					invader = e2;
					shoot = e1;
				}
				invader_c.setStateID(InvaderStateID.DYING);
				invader_c.setDyingTime(GameConfigData.INVADER.EXPLOSION_DELAY);
				ExplodingInvaderC explo_c = new ExplodingInvaderC(6, GameConfigData.INVADER.EXPLOSION_DELAY/5);
				manager.addComponent(invader, explo_c);
				AnimationSpriteC anim_c = (AnimationSpriteC) manager.componentFor(invader, ActorComponentID.ANIMATION);
				if(anim_c != null)
					manager.removeComponent(invader, anim_c);
				//manager.addComponent(invader, new AnimationSpriteC(GameResources.INVADER.EXPLOSION, GameConfigData.INVADER.EXPLOSION_DELAY));
				manager.markEntityForRemove(shoot);
			}
		}, EnumSet.of(EventType.COLLISION));
	}
	@Override
	public void update(float dt) {
		
	}
}
