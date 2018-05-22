package com.game.engine.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation implements Component {
	private float duration;
	private boolean loop;
	private TextureRegion[] sprites;
	
	public Animation(TextureRegion[] sprites, float duration, boolean loop) {
		this.duration = duration;
		this.sprites = sprites;
		this.loop = loop;
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
}
