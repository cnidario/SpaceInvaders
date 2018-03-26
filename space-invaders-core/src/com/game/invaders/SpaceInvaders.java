package com.game.invaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.invaders.subsystem.collision.CollisionManager;
import com.game.invaders.subsystem.event.EventManager;
import com.game.invaders.subsystem.input.InputManager;
import com.game.invaders.subsystem.physics.PhysicsManager;
import com.game.invaders.subsystem.process.ProcessManager;
import com.game.invaders.subsystem.render.RenderManager;

public class SpaceInvaders extends ApplicationAdapter {
	private SpriteBatch batch;
	private EventManager event_manager;
	private ProcessManager process_manager;
	private RenderManager render_manager;
	private GameWorld game_world;
	private long lastTime;
	
	@Override
	public void create () {
		lastTime = 0;
		batch = new SpriteBatch();
		
		event_manager = new EventManager();
		process_manager = new ProcessManager(event_manager);
		game_world = new GameWorld(event_manager);
		render_manager = new RenderManager();
		
		process_manager.addProcess(new InputManager(event_manager));
		process_manager.addProcess(new CollisionManager(event_manager));
		process_manager.addProcess(new PhysicsManager(event_manager));
		process_manager.addProcess(event_manager);
		
		process_manager.init();
		game_world.init();
		render_manager.init();
	}
	private void updatePhase() {
		int elapsedTime;
		long currentTime = System.nanoTime();
		if(lastTime == 0) elapsedTime = 0;
		else elapsedTime = (int) ((currentTime - lastTime)/1000000);
		lastTime = currentTime;
		
		process_manager.update(elapsedTime);
		game_world.update(elapsedTime);
	}
	@Override
	public void render () {
		updatePhase();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		render_manager.render();
		batch.end();
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}
