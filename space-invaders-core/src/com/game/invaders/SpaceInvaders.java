package com.game.invaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.base.EntityLifeCycleSystem;
import com.game.engine.system.collision.CollisionSystem;
import com.game.engine.system.controller.ControllerSystem;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.input.InputSystem;
import com.game.engine.system.physics.MotionSystem;
import com.game.engine.system.process.ProcessManager;
import com.game.engine.system.render.AnimationSystem;
import com.game.engine.system.render.RenderSystem;
import com.game.invaders.data.GameResources;
import com.game.invaders.system.ShootImpactManager;
import com.game.invaders.system.invader.ExplodingTiltSystem;
import com.game.invaders.system.invader.InvaderBehaviourSystem;
import com.game.invaders.system.invader.InvaderGroupMovementSystem;
import com.game.invaders.system.invader.InvaderStateSystem;
import com.game.invaders.system.player.PlayerBehaviourSystem;

public class SpaceInvaders extends ApplicationAdapter {
	private EntityManager entityManager;
	private EventSystem eventManager;
	private ProcessManager process_manager;
	private RenderSystem render_manager;
	private GameWorld game_world;
	private long lastTime;
	
	@Override
	public void create () {
		lastTime = 0;
		
		eventManager = new EventSystem();
		entityManager = new EntityManager(eventManager);
		process_manager = new ProcessManager();
		game_world = new GameWorld(eventManager, entityManager);
		render_manager = new RenderSystem(entityManager, eventManager);
		
		process_manager.addProcess(new InputSystem(eventManager));
		process_manager.addProcess(new ControllerSystem(entityManager, eventManager));
		process_manager.addProcess(new PlayerBehaviourSystem(entityManager, eventManager));
		process_manager.addProcess(new InvaderBehaviourSystem(entityManager, eventManager));
		process_manager.addProcess(new InvaderGroupMovementSystem(entityManager, eventManager));
		process_manager.addProcess(new InvaderStateSystem(entityManager, eventManager));
		process_manager.addProcess(new MotionSystem(entityManager, eventManager));
		process_manager.addProcess(new CollisionSystem(entityManager, eventManager));
		process_manager.addProcess(new ShootImpactManager(entityManager, eventManager));
		process_manager.addProcess(new AnimationSystem(entityManager, eventManager));
		process_manager.addProcess(new ExplodingTiltSystem(entityManager, eventManager));
		process_manager.addProcess(new EntityLifeCycleSystem(entityManager, eventManager));
		process_manager.addProcess(eventManager);
		
		process_manager.init();
		game_world.init();
		render_manager.init();
		GameResources.GAME.MAIN_SONG.play();
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
