package com.game.invaders.actor.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.invaders.actor.Actor;

public interface RenderStrategy {
	void render(Actor actor, SpriteBatch batch);
}
