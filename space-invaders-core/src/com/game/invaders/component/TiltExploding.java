package com.game.invaders.component;

import com.game.engine.entity.AbstractComponent;

public class TiltExploding extends AbstractComponent {
	private float tilt;
	private float period;
	private float elapsed;
	
	public TiltExploding(float tilt, float period) {
		super(ComponentID.TILT_EXPLODING);
		this.tilt = tilt;
		this.period = period;
		this.elapsed = 0;
	}
	public float getTilt() {
		return tilt;
	}
	public void setTilt(float tilt) {
		this.tilt = tilt;
	}
	public float getPeriod() {
		return period;
	}
	public void setPeriod(float period) {
		this.period = period;
	}
	public float getElapsed() {
		return elapsed;
	}
	public void setElapsed(float elapsed) {
		this.elapsed = elapsed;
	}
	
}
