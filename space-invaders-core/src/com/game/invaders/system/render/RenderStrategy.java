package com.game.invaders.system.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.invaders.scene.actor.Actor;

public interface RenderStrategy {
	void render(Actor actor, SpriteBatch batch);
}
