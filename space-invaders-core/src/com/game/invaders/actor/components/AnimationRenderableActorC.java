package com.game.invaders.actor.components;

import com.game.invaders.actor.ActorComponent;
import com.game.invaders.animation.AnimationPlayer;

public class AnimationRenderableActorC implements ActorComponent {
	private AnimationPlayer animationPlayer;
	public AnimationPlayer getAnimationPlayer() {
		return animationPlayer;
	}
	public void setAnimationPlayer(AnimationPlayer animationPlayer) {
		this.animationPlayer = animationPlayer;
	}
	public AnimationRenderableActorC(AnimationPlayer animationPlayer) {
		super();
		this.animationPlayer = animationPlayer;
	}
	@Override
	public ActorComponentID getID() { return ActorComponentID.ANIMATION_RENDERABLE; }
}
