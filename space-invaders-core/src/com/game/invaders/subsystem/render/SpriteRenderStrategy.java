package com.game.invaders.subsystem.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.invaders.scene.actor.Actor;

public class SpriteRenderStrategy implements RenderStrategy {
	private TextureRegion tex;
	public SpriteRenderStrategy(TextureRegion tex) {
		super();
		this.tex = tex;
	}
	public TextureRegion getTex() {
		return tex;
	}
	public void setTex(TextureRegion tex) {
		this.tex = tex;
	}
	@Override
	public void render(Actor actor, SpriteBatch batch) {
		batch.draw(tex, actor.getPos().x, actor.getPos().y);
	}
}
