package com.game.invaders.actor.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.invaders.actor.Actor;
import com.game.invaders.animation.AnimationPlayer;

public class AnimationRenderStrategy implements RenderStrategy {
	private AnimationPlayer animationPlayer;
	public AnimationRenderStrategy(AnimationPlayer animationPlayer) {
		super();
		this.animationPlayer = animationPlayer;
	}
	public AnimationPlayer getAnimationPlayer() {
		return animationPlayer;
	}
	public void setAnimationPlayer(AnimationPlayer animationPlayer) {
		this.animationPlayer = animationPlayer;
	}
	@Override
	public void render(Actor actor, SpriteBatch batch) {
		batch.draw(animationPlayer.getFrame(), actor.getPos().x, actor.getPos().y);
	}

}
