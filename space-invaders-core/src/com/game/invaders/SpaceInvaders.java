package com.game.invaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.game.invaders.system.collision.CollisionManager;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.input.InputManager;
import com.game.invaders.system.physics.PhysicsManager;
import com.game.invaders.system.process.ProcessManager;
import com.game.invaders.system.render.RenderManager;

public class SpaceInvaders extends ApplicationAdapter {
	private EventManager event_manager;
	private ProcessManager process_manager;
	private RenderManager render_manager;
	private GameWorld game_world;
	private long lastTime;
	
	@Override
	public void create () {
		lastTime = 0;
		
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
		render_manager.render();
	}
	@Override
	public void dispose () {
	}
}
