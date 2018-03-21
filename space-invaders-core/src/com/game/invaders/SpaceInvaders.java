package com.game.invaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.invaders.subsystem.collision.CollisionManager;
import com.game.invaders.subsystem.event.EventManager;
import com.game.invaders.subsystem.input.InputManager;
import com.game.invaders.subsystem.process.ProcessManager;

public class SpaceInvaders extends ApplicationAdapter {
	private SpriteBatch batch;
	private EventManager event_manager = new EventManager();
	private ProcessManager process_manager = new ProcessManager(event_manager);
	private CollisionManager collision_manager = new CollisionManager(event_manager);
	private InputManager input_manager = new InputManager(event_manager);
	private GameWorld game_world = new GameWorld(event_manager);
	private long lastTime;
	
	@Override
	public void create () {
		lastTime = 0;
		batch = new SpriteBatch();

		input_manager.init();
		collision_manager.init();
		process_manager.init();
		event_manager.init();
		game_world.init();
	}
	
	private void updatePhase() {
		int elapsedTime;
		long currentTime = System.nanoTime();
		if(lastTime == 0) elapsedTime = 0;
		else elapsedTime = (int) ((currentTime - lastTime)/1000000);
		lastTime = currentTime;
		
		input_manager.processInput();
		collision_manager.processCollisions();
		process_manager.update(elapsedTime);
		event_manager.processEvents();
		game_world.update(elapsedTime);
	}

	@Override
	public void render () {
		updatePhase();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		
		
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
