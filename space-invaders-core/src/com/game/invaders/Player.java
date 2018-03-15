package com.game.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
	private final static Texture SHIP_IMAGE =  new Texture("ship.png");
	private final static int YZERO = 100;
	private final static int XZERO = 500;
	private final static int X_MOVE_RESOLUTION = 24;
	
	private int xPos;
	public Player() {
		xPos = 0;
	}
	private float computePositionX() {
		return xPos*X_MOVE_RESOLUTION + XZERO;
	}
	private float computePositionY() {
		return YZERO;
	}
	
	public void handleInput() {
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xPos--;
			if(computePositionX() < 0)
				xPos++;
		} else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xPos++;
			if(computePositionX() > 900)
				xPos--;
		}
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(SHIP_IMAGE, computePositionX(), computePositionY());
	}

}
