package com.game.invaders.actor.components;

import com.game.invaders.actor.ActorComponent;

public class RenderableActorC implements ActorComponent {
	private RenderStrategy renderStrategy;
	public RenderableActorC(RenderStrategy renderStrategy) {
		this.renderStrategy = renderStrategy;
	}
	public RenderStrategy getRenderStrategy() {
		return renderStrategy;
	}
	public void setRenderStrategy(RenderStrategy renderStrategy) {
		this.renderStrategy = renderStrategy;
	}
	@Override
	public ActorComponentID getID() { return ActorComponentID.RENDERABLE; }
}
