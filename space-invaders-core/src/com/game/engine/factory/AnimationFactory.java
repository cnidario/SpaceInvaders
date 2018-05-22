package com.game.engine.factory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.engine.component.Animation;
import com.game.engine.component.AnimationInstance;
import com.game.engine.node.Node;

public class AnimationFactory {
	public AnimationFactory() {
	}
	public Node create(Node root, TextureRegion[] sprites, float duration, boolean loop) {
		return root.create(
				new Animation(sprites, duration, loop)
				);
	}
	public AnimationInstance newInstance(Node animation) {
		return new AnimationInstance(animation.getId());
	}
}
