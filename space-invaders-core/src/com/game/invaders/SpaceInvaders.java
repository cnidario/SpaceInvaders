package com.game.invaders;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceInvaders extends ApplicationAdapter {
	private SpriteBatch batch;
	private long lastTime = 0;
	private List<Invader> enemies;
	private Player player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		enemies = new ArrayList<Invader>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				enemies.add(new Invader(j*96, -i*76, 0, 0));
			}
		}
		player = new Player();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		int elapsedTime;
		long currentTime = System.nanoTime();
		if(lastTime == 0) elapsedTime = 0;
		else elapsedTime = (int) ((currentTime - lastTime)/1000000);
		lastTime = currentTime;
		for(Invader enemy : enemies) {
			enemy.update(elapsedTime);
		}
		player.handleInput();
		batch.begin();
		for(Invader enemy : enemies) {
			enemy.render(batch);
		}
		player.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
