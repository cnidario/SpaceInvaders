package com.game.invaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.system.collision.CollisionManager;
import com.game.invaders.system.controller.ControllerManager;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.input.InputManager;
import com.game.invaders.system.logic.invader.InvaderBehaviourSystem;
import com.game.invaders.system.logic.invader.InvaderGroupMovementSystem;
import com.game.invaders.system.logic.player.PlayerBehaviourSystem;
import com.game.invaders.system.physics.PhysicsManager;
import com.game.invaders.system.process.ProcessManager;
import com.game.invaders.system.render.RenderManager;

public class SpaceInvaders extends ApplicationAdapter {
	private EntityManager entityManager;
	private EventManager eventManager;
	private ProcessManager process_manager;
	private RenderManager render_manager;
	private GameWorld game_world;
	private long lastTime;
	
	@Override
	public void create () {
		lastTime = 0;
		
		eventManager = new EventManager();
		entityManager = new EntityManager(eventManager);
		process_manager = new ProcessManager();
		game_world = new GameWorld(eventManager, entityManager);
		render_manager = new RenderManager(entityManager, eventManager);
		
		process_manager.addProcess(new InputManager(eventManager));
		process_manager.addProcess(new ControllerManager(entityManager, eventManager));
		process_manager.addProcess(new PlayerBehaviourSystem(entityManager, eventManager));
		process_manager.addProcess(new InvaderBehaviourSystem(entityManager, eventManager));
		process_manager.addProcess(new InvaderGroupMovementSystem(entityManager, eventManager));
		process_manager.addProcess(new PhysicsManager(entityManager, eventManager));
		process_manager.addProcess(new CollisionManager(entityManager, eventManager));
		process_manager.addProcess(eventManager);
		
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
