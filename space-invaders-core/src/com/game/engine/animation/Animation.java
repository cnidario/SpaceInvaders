package com.game.engine.animation;

import java.util.List;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	private List<TextureRegion> frames;
	private boolean loop;
	public Animation(List<TextureRegion> frames, boolean loop) {
		super();
		this.frames = frames;
		this.loop = loop;
	}
	public List<TextureRegion> getFrames() {
		return frames;
	}
	public boolean isLoop() {
		return loop;
	}
}
