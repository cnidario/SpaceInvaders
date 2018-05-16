package com.game.engine.component;

public class Impact implements Component {
	private int e1, e2;
	//private float time;

	public Impact(int e1, int e2) {
		super();
		this.e1 = e1;
		this.e2 = e2;
	}
	public int getE1() {
		return e1;
	}
	public void setE1(int e1) {
		this.e1 = e1;
	}
	public int getE2() {
		return e2;
	}
	public void setE2(int e2) {
		this.e2 = e2;
	}
}
