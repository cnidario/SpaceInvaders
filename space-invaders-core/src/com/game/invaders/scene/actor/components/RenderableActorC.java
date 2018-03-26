package com.game.invaders.scene.actor.components;

import com.game.invaders.scene.actor.Actor;

public class RenderableActorC extends AbstractActorComponent {
	private RenderStrategy renderStrategy;
	public RenderableActorC(Actor parent, RenderStrategy renderStrategy) {
		super(parent, ActorComponentID.RENDERABLE);
		this.renderStrategy = renderStrategy;
	}
	public RenderStrategy getRenderStrategy() {
		return renderStrategy;
	}
	public void setRenderStrategy(RenderStrategy renderStrategy) {
		this.renderStrategy = renderStrategy;
	}
}
