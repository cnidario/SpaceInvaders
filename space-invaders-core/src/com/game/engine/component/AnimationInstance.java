package com.game.engine.component;

public class AnimationInstance implements Component {
	private float elapsed;
	private int parent;
	
	public AnimationInstance(int parent) {
		super();
		this.elapsed = 0;
		this.parent = parent;
	}
	public float getElapsed() {
		return elapsed;
	}
	public void setElapsed(float elapsed) {
		this.elapsed = elapsed;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
}
