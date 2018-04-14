package com.game.invaders;

import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.base.EntityLifeCycleSystem;
import com.game.engine.system.collision.CollisionSystem;
import com.game.engine.system.controller.ControllerSystem;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.input.InputSystem;
import com.game.engine.system.motion.MotionSystem;
import com.game.engine.system.process.ProcessManager;
import com.game.engine.system.render.AnimationSystem;
import com.game.engine.system.render.RenderSystem;
import com.game.invaders.data.GameResources;
import com.game.invaders.event.InvaderDestroyedEvent;
import com.game.invaders.event.InvaderImpactedEvent;
import com.game.invaders.event.ShootCreatedEvent;
import com.game.invaders.factory.ImpactEventFactory;
import com.game.invaders.factory.InvaderDestroyedEventFactory;
import com.game.invaders.factory.InvaderFactory;
import com.game.invaders.factory.InvaderGroupFactory;
import com.game.invaders.factory.ShootFactory;
import com.game.invaders.system.impact.CollisionGroup;
import com.game.invaders.system.impact.ImpactDetectionSystem;
import com.game.invaders.system.impact.InvaderImpactSystem;
import com.game.invaders.system.invader.ExplodingTiltSystem;
import com.game.invaders.system.invader.InvaderBehaviourSystem;
import com.game.invaders.system.invader.InvaderGroupMovementSystem;
import com.game.invaders.system.invader.InvaderStateSystem;
import com.game.invaders.system.player.PlayerBehaviourSystem;
import com.game.invaders.system.sound.SimpleSoundResponse;
import com.game.invaders.system.sound.SoundSystem;

public class SpaceInvaders extends ApplicationAdapter {
	private EntityManager entityManager;
	private EventSystem eventSystem;
	private ProcessManager process_manager;
	private RenderSystem render_manager;
	private GameWorld game_world;
	private long lastTime;
	
	@Override
	public void create () {
		lastTime = 0;
		
		Random rnd = new Random();		
		eventSystem = new EventSystem();
		entityManager = new EntityManager(eventSystem);
		
		ShootFactory shootFactory = new ShootFactory(entityManager, eventSystem);
		InvaderFactory invaderFactory = new InvaderFactory(entityManager, eventSystem);
		InvaderGroupFactory invaderGroupFactory = new InvaderGroupFactory(entityManager, eventSystem);
		ImpactEventFactory impactFactory = new ImpactEventFactory(eventSystem);
		InvaderDestroyedEventFactory invaderDestroyedFactory = new InvaderDestroyedEventFactory(eventSystem);
		
		process_manager = new ProcessManager();
		game_world = new GameWorld(eventSystem, entityManager, invaderFactory, invaderGroupFactory);
		render_manager = new RenderSystem(entityManager, eventSystem);
		
		SoundSystem soundSystem = new SoundSystem(eventSystem);
		soundSystem.addSoundReponse(new SimpleSoundResponse(rnd, GameResources.GAME.SHOOTS, ShootCreatedEvent.class));
		soundSystem.addSoundReponse(new SimpleSoundResponse(rnd, GameResources.GAME.HITS, InvaderImpactedEvent.class));
		soundSystem.addSoundReponse(new SimpleSoundResponse(rnd, GameResources.GAME.EXPLOSIONS, InvaderDestroyedEvent.class));
		
		process_manager.addProcess(new InputSystem(eventSystem));
		process_manager.addProcess(new ControllerSystem(entityManager, eventSystem));
		process_manager.addProcess(new PlayerBehaviourSystem(entityManager, eventSystem, shootFactory));
		process_manager.addProcess(new InvaderBehaviourSystem(entityManager, eventSystem));
		process_manager.addProcess(new InvaderGroupMovementSystem(entityManager, eventSystem));
		process_manager.addProcess(new InvaderStateSystem(entityManager, eventSystem,invaderDestroyedFactory));
		process_manager.addProcess(new MotionSystem(entityManager, eventSystem));
		process_manager.addProcess(new CollisionSystem<CollisionGroup>(entityManager, eventSystem));
		process_manager.addProcess(new ImpactDetectionSystem(entityManager, eventSystem, impactFactory));
		process_manager.addProcess(new InvaderImpactSystem(entityManager, eventSystem));
		process_manager.addProcess(new AnimationSystem(entityManager, eventSystem));
		process_manager.addProcess(new ExplodingTiltSystem(entityManager, eventSystem));
		process_manager.addProcess(soundSystem);
		process_manager.addProcess(new EntityLifeCycleSystem(entityManager, eventSystem));
		process_manager.addProcess(eventSystem);
		
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
