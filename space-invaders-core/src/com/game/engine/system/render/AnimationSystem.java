package com.game.engine.system.render;

import com.game.engine.entity.component.Animation;
import com.game.engine.entity.component.Renderable;
import com.game.engine.system.entity.node.EntityNodeSetFactory;
import com.game.engine.system.entity.node.Node;
import com.game.engine.system.entity.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;

public class AnimationSystem extends AbstractProcess {
	private NodeSet nodeSet;
	
	@SuppressWarnings("unchecked")
	public AnimationSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		nodeSet = entityNodeSetFactory.create(Animation.class, Renderable.class);
	}
	@Override
	public void update(float dt) {
		for (Node node : nodeSet) {
			Animation anim_c = (Animation) node.component(Animation.class);
			Renderable render_c = (Renderable) node.component(Renderable.class);
			float elapsed = anim_c.getElapsed() + dt;
			anim_c.setElapsed(elapsed);
			if(elapsed >= anim_c.getDuration()) {
				if(anim_c.isLoop()) {
					anim_c.setElapsed(elapsed - anim_c.getDuration());
				} else {
					node.deleteComponent(Animation.class);
				}
			} else {
				int ix = (int) Math.floor((elapsed / anim_c.getDuration()) * anim_c.getSprites().length);
				render_c.setTex(anim_c.getSprites()[ix]);
			}
		}
	}
}
