package com.game.invaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Invader {
	private final static Texture image =  new Texture("invader1.png");
	private final static TextureRegion[] invaders = { new TextureRegion(image, 64, 64), new TextureRegion(image, 64, 0, 64, 64)};
	private final static int MOVE_RESOLUTION_IN_PIXELS = 16;
	private final static int NUM_HORIZ_MOVES = 10;
	private final static int TIME_PER_MOVE = 1000;
	private final static int MIN_TIME_PER_MOVE = 200;
	private final static int GLOBAL_XZERO = 100;
	private final static int GLOBAL_YZERO = 700;
	
	private int horizontal_pos, vertical_pos;
	private int internalCounter, x0, y0;
	
	public Invader(int x0, int y0, int horizontal_pos, int vertical_pos) {
		this.x0 = x0;
		this.y0 = y0;
		this.horizontal_pos = horizontal_pos ;
		this.vertical_pos = vertical_pos;
		internalCounter = 0;
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
		return internalCounter > timePerMove()/2? invaders[1]: invaders[0];
	}
	private float computePositionX() {
		return horizontal_pos*MOVE_RESOLUTION_IN_PIXELS + GLOBAL_XZERO + x0;
	}
	private float computePositionY() {
		return -vertical_pos*MOVE_RESOLUTION_IN_PIXELS + GLOBAL_YZERO + y0;
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
		int tpm = timePerMove();
		if(internalCounter > tpm) {
			internalCounter %= tpm;
			doStep();
		}
	}
	public void render(SpriteBatch batch) {
		batch.draw(currentFrame(), computePositionX(), computePositionY());
	}
}
