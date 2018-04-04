package com.game.invaders.scene.actor.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationSpriteC extends AbstractActorComponent {
	private float duration;
	private boolean loop;
	private TextureRegion[] sprites;
	private float elapsed;
	
	public AnimationSpriteC(TextureRegion[] sprites, float duration, boolean loop) {
		super(ActorComponentID.ANIMATION);
		this.duration = duration;
		this.sprites = sprites;
		this.loop = loop;
		elapsed = 0;
	}
	public TextureRegion[] getSprites() {
		return sprites;
	}
	public void setSprites(TextureRegion[] sprites) {
		this.sprites = sprites;
	}
	public float getDuration() {
		return duration;
	}
	public void setDuration(float duration) {
		this.duration = duration;
	}
	public boolean isLoop() {
		return loop;
	}
	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	public float getElapsed() {
		return elapsed;
	}
	public void setElapsed(float elapsed) {
		this.elapsed = elapsed;
	}
}
