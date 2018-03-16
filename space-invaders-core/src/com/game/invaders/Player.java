package com.game.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {
	private final static Texture SHIP_IMAGE =  new Texture("ship.png");
	private final static int YZERO = 100;
	private final static int XZERO = 500;
	private final static int XMIN = 75;
	private final static int XMAX = 900;
	public final static int YSHOOT_MIN = 50;
	private final static int XSHOOT_ZERO = 42;
	private final static int YSHOOT_ZERO = 20;
	private final static int SHOOT_DELAY = 250;
	private final static int MOVE_SPEED = 500;
	private static enum MovingState {
		NO_MOVING,
		MOVING_LEFT,
		MOVING_RIGHT
	}
	
	private int xPos;
	private MovingState moving;
	private boolean shooting;
	private int shoot_timer;
	private SpaceInvaders game;
	
	public Player(SpaceInvaders game) {
		xPos = XZERO;
		shooting = false;
		moving = MovingState.NO_MOVING;
		shoot_timer = 0; 
		this.game = game;
	}
	private float computePositionX() {
		return xPos;
	}
	private float computePositionY() {
		return YZERO;
	}
	
	public void handleInput() {
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			moving = MovingState.MOVING_LEFT;
		} else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			moving = MovingState.MOVING_RIGHT;
		} else {
			moving = MovingState.NO_MOVING;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			shooting = true;
		}
	}
	
	public void update(float dt) {
		if(moving != MovingState.NO_MOVING) {
			int dx = Math.round(MOVE_SPEED * dt/1000);
			if(moving == MovingState.MOVING_LEFT)
				dx = -dx;
			xPos += dx;
			if(xPos < XMIN)
				xPos = XMIN;
			else if(xPos > XMAX)
				xPos = XMAX;
		}
		shoot_timer -= (int) dt;
		if(shoot_timer < 0)
			shoot_timer = 0;
		if(shooting && shoot_timer == 0) {
			game.shoot((int)computePositionX() + XSHOOT_ZERO, (int)computePositionY() + YSHOOT_ZERO);
			shooting = false;
			shoot_timer = SHOOT_DELAY;
		}
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(SHIP_IMAGE, computePositionX(), computePositionY());
	}

}
