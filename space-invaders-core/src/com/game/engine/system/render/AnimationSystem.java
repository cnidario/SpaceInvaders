package com.game.engine.system.render;

import java.util.HashSet;
import java.util.Set;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.component.Animation;
import com.game.engine.entity.component.Renderable;
import com.game.engine.system.entity.EntityMapper;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.process.AbstractProcess;

public class AnimationSystem extends AbstractProcess {
	private EntityManager manager;
	private EntityMapper managedEntities;
	private EventSystem eventManager;
	
	public AnimationSystem(EntityManager manager, EventSystem eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		Set<Class<? extends Component>> cs = new HashSet<Class<? extends Component>>();
		cs.add(Animation.class);
		cs.add(Renderable.class);
		managedEntities = new EntityMapper(manager, eventManager, cs);
	}
	@Override
	public void update(float dt) {
		for(IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			Animation anim_c = (Animation) manager.componentFor(e, Animation.class);
			Renderable render_c = (Renderable) manager.componentFor(e, Renderable.class);
			float elapsed = anim_c.getElapsed() + dt;
			anim_c.setElapsed(elapsed);
			if(elapsed >= anim_c.getDuration()) {
				if(anim_c.isLoop()) {
					anim_c.setElapsed(elapsed - anim_c.getDuration());
				} else {
					manager.markComponentForRemove(e, Animation.class);
				}
			} else {
				int ix = (int) Math.floor((elapsed / anim_c.getDuration()) * anim_c.getSprites().length);
				render_c.setTex(anim_c.getSprites()[ix]);
			}
		}
	}
}
