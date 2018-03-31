package com.game.invaders.scene.actor.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderableActorC extends AbstractActorComponent {
	private TextureRegion tex;
	
	public RenderableActorC(TextureRegion tex) {
		super(ActorComponentID.RENDERABLE);
		this.tex = tex;
	}
	public TextureRegion getTex() {
		return tex;
	}
	public void setTex(TextureRegion tex) {
		this.tex = tex;
	}
}
