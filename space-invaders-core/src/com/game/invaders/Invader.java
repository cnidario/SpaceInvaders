package com.game.invaders;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.invaders.process.FrameSource;
import com.game.invaders.process.InterpolateAnimation;

public class Invader {
	private final static Texture image =  new Texture("invader1.png"); 
	private final static TextureRegion[] invaders = { new TextureRegion(image, 64, 64), new TextureRegion(image, 64, 0, 64, 64)};
	private final static int MOVE_RESOLUTION_IN_PIXELS = 16;
	private final static int NUM_HORIZ_MOVES = 10;
	private final static int TIME_PER_MOVE = 1000;
	private final static int MIN_TIME_PER_MOVE = 200;
	private final static int GLOBAL_XZERO = 100;
	private final static int GLOBAL_YZERO = 700;
	private final static int DIE_DELAY = 700;
	
	public final static InterpolateAnimation MOVE_ANIMATION = new InterpolateAnimation(Arrays.asList(invaders), TIME_PER_MOVE);
	
	public static enum InvaderState {
		ALIVE, DYING, DIED
	}
	
	public final static BoundingBox BBOX = new BoundingBox(image.getWidth(), image.getHeight());
	
	private int horizontal_pos = 0, vertical_pos = 0;
	private int internalCounter;
	private float x0, y0;
	private InvaderState state;
	private FrameSource frameSource;
	
	public FrameSource getFrameSource() {
		return frameSource;
	}
	public void setFrameSource(FrameSource frameSource) {
		this.frameSource = frameSource;
	}
	
	public InvaderState getState() {
		return state;
	}
	public void setState(InvaderState state) {
		this.state = state;
	}
	public Invader(float x0, float y0) {
		this.x0 = x0;
		this.y0 = y0;
		internalCounter = 0;
		state = InvaderState.ALIVE;
	}
	/**
	 * Tiempo en milisegundos para efectuar un desplazamiento lateral, va aumentando la velocidad con el avance vertical
	 */
	private int timePerMove() {
		int tpm = TIME_PER_MOVE - vertical_pos*(TIME_PER_MOVE/10);
		if(tpm < MIN_TIME_PER_MOVE)
			return MIN_TIME_PER_MOVE;
		return tpm;
	}
	/**
	 * En cada movimiento se cambia alterna entre dos frames para la animación del invasor
	 */
	private TextureRegion currentFrame() {
		return frameSource.currentFrame();
	}
	private float computePositionX() {
		return horizontal_pos*MOVE_RESOLUTION_IN_PIXELS + GLOBAL_XZERO + x0;
	}
	private float computePositionY() {
		return -vertical_pos*MOVE_RESOLUTION_IN_PIXELS + GLOBAL_YZERO + y0;
	}
	public float getX() {
		return computePositionX();
	}
	public float getY() {
		return computePositionY();
	}
		
	private void doStep() {
		horizontal_pos++;
		if(horizontal_pos > NUM_HORIZ_MOVES) {
			horizontal_pos = 0;
			vertical_pos++;
		}
	}
	
	public void update(float dt) {
		internalCounter += dt;
		if(state == InvaderState.ALIVE) {
			int tpm = timePerMove();
			if(internalCounter > tpm) {
				internalCounter %= tpm;
				doStep();
			}
		} else if(state == InvaderState.DYING){
			if(internalCounter > DIE_DELAY)
				state = InvaderState.DIED;
		}
	}
	public void render(SpriteBatch batch) {
		batch.draw(currentFrame(), computePositionX(), computePositionY());
	}
}
