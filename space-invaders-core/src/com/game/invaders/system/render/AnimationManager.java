package com.game.invaders.system.render;

import java.util.EnumSet;

import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.component.AnimationSpriteC;
import com.game.invaders.scene.actor.component.InvaderStateC;
import com.game.invaders.scene.actor.component.RenderableActorC;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.system.engine.EntityMapper;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.process.AbstractProcess;

public class AnimationManager extends AbstractProcess {
	private EntityManager manager;
	private EntityMapper managedEntities;
	private EventManager eventManager;
	
	public AnimationManager(EntityManager manager, EventManager eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ActorComponentID.ANIMATION, ActorComponentID.RENDERABLE));
	}
	@Override
	public void update(float dt) {
		for(IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			AnimationSpriteC anim_c = (AnimationSpriteC) manager.componentFor(e, ActorComponentID.ANIMATION);
			RenderableActorC render_c = (RenderableActorC) manager.componentFor(e, ActorComponentID.RENDERABLE);
			float elapsed = anim_c.getElapsed() + dt;
			anim_c.setElapsed(elapsed);
			if(elapsed >= anim_c.getDuration()) {
				if(anim_c.isLoop()) {
					anim_c.setElapsed(elapsed - anim_c.getDuration());
				} else {
					manager.removeComponent(e, anim_c);
				}
			} else {
				int ix = (int) Math.floor((elapsed / anim_c.getDuration()) * anim_c.getSprites().length);
				render_c.setTex(anim_c.getSprites()[ix]);
			}
		}
	}
}
