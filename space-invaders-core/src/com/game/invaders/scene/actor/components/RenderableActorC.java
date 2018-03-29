package com.game.invaders.scene.actor.components;

import com.game.invaders.system.render.RenderStrategy;

public class RenderableActorC extends AbstractActorComponent {
	private RenderStrategy renderStrategy;
	public RenderableActorC(RenderStrategy renderStrategy) {
		super(ActorComponentID.RENDERABLE);
		this.renderStrategy = renderStrategy;
	}
	public RenderStrategy getRenderStrategy() {
		return renderStrategy;
	}
	public void setRenderStrategy(RenderStrategy renderStrategy) {
		this.renderStrategy = renderStrategy;
	}
}
