package com.game.invaders.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationPlayer {
	private Animation animation;
	private int time;
	private int elapsed;
	private int frame_count;
	public AnimationPlayer(Animation animation, int time) {
		super();
		this.animation = animation;
		this.time = time;
		frame_count = animation.getFrames().size();
	}
	public void init() {
		elapsed = 0;
	}
	public void update(float dt) {
		elapsed += dt;
		if(elapsed > time && animation.isLoop())
			elapsed = 0;
		else ; //end
	}
	private int calcFrame(int elapsedTime) {
		int time_gap = time / frame_count;
		int frame_number = elapsedTime / time_gap;
		if(frame_number >= frame_count)
			frame_number = frame_count - 1;
		return frame_number;
	}
	public TextureRegion getFrame() {
		return animation.getFrames().get(calcFrame(elapsed));
	}
}
