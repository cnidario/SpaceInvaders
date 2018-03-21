package com.game.invaders.actor.invader;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.game.invaders.actor.Actor;

public class Invader extends Actor {
	private InvaderStateMachine state = new InvaderStateMachine();
	
	public Invader(float x, float y) {
		setPos(new Vector2(x,y));
	}
	@Override
	public void update(float dt) {
		state.update(dt, this);
	}
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(state.getState().getAnimation().getFrame(), getPos().x, getPos().y);
	}
}
