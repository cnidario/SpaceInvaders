package com.game.invaders.actor.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.invaders.actor.ActorComponent;

public class SpriteRenderableActorC implements ActorComponent {
	private TextureRegion region;
	public SpriteRenderableActorC(TextureRegion region) {
		this.region = region;
	}
	@Override
	public ActorComponentID getID() { return ActorComponentID.RENDERABLE; }
	public TextureRegion getRegion() {
		return region;
	}
	public void setRegion(TextureRegion region) {
		this.region = region;
	}
}
