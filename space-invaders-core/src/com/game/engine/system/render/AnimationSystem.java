package com.game.engine.system.render;

import com.game.engine.component.Animation;
import com.game.engine.component.AnimationInstance;
import com.game.engine.component.Renderable;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.node.Node;
import com.game.engine.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;

public class AnimationSystem extends AbstractProcess {
	private NodeSet nodeSet;
	
	@SuppressWarnings("unchecked")
	public AnimationSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		nodeSet = entityNodeSetFactory.create(AnimationInstance.class, Renderable.class);
	}
	@Override
	public void update(float dt) {
		for (Node node : nodeSet) {
			AnimationInstance animInstance_c = (AnimationInstance) node.component(AnimationInstance.class);
			Animation anim_c = (Animation) node.asNode(animInstance_c.getParent()).component(Animation.class);
			Renderable render_c = (Renderable) node.component(Renderable.class);
			float elapsed = animInstance_c.getElapsed() + dt;
			animInstance_c.setElapsed(elapsed);
			if(elapsed >= anim_c.getDuration()) {
				if(anim_c.isLoop()) {
					animInstance_c.setElapsed(elapsed - anim_c.getDuration());
				} else {
					node.deleteComponent(AnimationInstance.class);
				}
			} else {
				int ix = (int) Math.floor((elapsed / anim_c.getDuration()) * anim_c.getSprites().length);
				render_c.setTex(anim_c.getSprites()[ix]);
			}
		}
	}
}
