package com.game.invaders.actor.shoot;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.invaders.subsystem.collision.BoundingBox;

public class Shoot {
	private final static Texture SHOOT = new Texture("shoot.png");
	private final static int SPEED = 500;
	
	public final static BoundingBox BBOX = new BoundingBox(SHOOT.getWidth(), SHOOT.getHeight());
	
	private int xPos, yPos;
	public Shoot(int x, int y) {
		xPos = x;
		yPos = y;
	}
	public int getxPos() {
		return xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void update(float dt) {
		int dy = Math.round(SPEED * dt/1000);
		yPos += dy;
	}
	public void render(SpriteBatch batch) {
		batch.draw(SHOOT, xPos, yPos);
	}
}
