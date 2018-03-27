package com.game.invaders.system.collision;

public class BoundingBox {
	private int width, height;
	
	public BoundingBox(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
