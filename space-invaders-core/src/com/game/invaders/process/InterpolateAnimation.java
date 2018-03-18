package com.game.invaders.process;

import java.util.List;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InterpolateAnimation extends AbstractProcess implements Frameable {
	private List<TextureRegion> frames;
	private int time;
	private int counter;
	public InterpolateAnimation(List<TextureRegion> frames, int time) {
		this.frames = frames;
		this.time = time;
		counter = 0;
	}
	private int calcFrame(int elapsedTime) {
		int time_gap = time / frames.size();
		int frame_number = elapsedTime / time_gap;
		if(frame_number >= frames.size())
			frame_number = frames.size() - 1;
		return frame_number;
	}
	public TextureRegion currentFrame() {
		return frames.get(calcFrame(counter));
	}
	@Override
	public void update(float dt) {
		counter += dt;
		if(counter > time) {
			kill();
		}
	}
	@Override
	public void restart() {
		super.restart();
		counter = 0;
	}
}
