package com.game.invaders.subsystem.render;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.invaders.actor.Actor;

public class RenderManager {
	private SpriteBatch batch = new SpriteBatch();
	private List<Actor> actors = new ArrayList<Actor>();
	
	public void addActor(Actor o) {
		actors.add(o);
	}
	
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Actor actor : actors) {
			if(actor.isActive() && actor.isVisible()) {
			}
		}
		batch.end();
	}
}
