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
import com.game.invaders.factory.EntityBuilderFactory;
import com.game.invaders.factory.ImpactEventFactory;
import com.game.invaders.factory.InvaderDestroyedEventFactory;
import com.game.invaders.factory.InvaderFactory;
import com.game.invaders.factory.InvaderGroupFactory;
import com.game.invaders.factory.PlayerShipFactory;
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
	private ProcessManager processManager;
	private RenderSystem renderSystem;
	private GameWorld gameWorld;
	private long lastTime;
	
	@Override
	public void create () {
		lastTime = 0;
		
		Random rnd = new Random();		
		eventSystem = new EventSystem();
		entityManager = new EntityManager(eventSystem);
		
		EntityBuilderFactory entityBuilderFactory = new EntityBuilderFactory();
		ShootFactory shootFactory = new ShootFactory(entityManager, eventSystem, entityBuilderFactory);
		InvaderFactory invaderFactory = new InvaderFactory(entityManager, eventSystem, entityBuilderFactory);
		InvaderGroupFactory invaderGroupFactory = new InvaderGroupFactory(entityManager, eventSystem);
		ImpactEventFactory impactFactory = new ImpactEventFactory(eventSystem);
		InvaderDestroyedEventFactory invaderDestroyedFactory = new InvaderDestroyedEventFactory(eventSystem);
		PlayerShipFactory playerShipFactory = new PlayerShipFactory(entityManager, eventSystem, entityBuilderFactory);
		
		processManager = new ProcessManager();
		gameWorld = new GameWorld(eventSystem, entityManager, invaderFactory, invaderGroupFactory, playerShipFactory);
		renderSystem = new RenderSystem(entityManager, eventSystem);
		
		SoundSystem soundSystem = new SoundSystem(eventSystem);
		soundSystem.addSoundReponse(new SimpleSoundResponse(rnd, GameResources.GAME.SHOOTS, ShootCreatedEvent.class));
		soundSystem.addSoundReponse(new SimpleSoundResponse(rnd, GameResources.GAME.HITS, InvaderImpactedEvent.class));
		soundSystem.addSoundReponse(new SimpleSoundResponse(rnd, GameResources.GAME.EXPLOSIONS, InvaderDestroyedEvent.class));
		
		processManager.addProcess(new InputSystem(eventSystem));
		processManager.addProcess(new ControllerSystem(entityManager, eventSystem));
		processManager.addProcess(new PlayerBehaviourSystem(entityManager, eventSystem, shootFactory));
		processManager.addProcess(new InvaderBehaviourSystem(entityManager, eventSystem));
		processManager.addProcess(new InvaderGroupMovementSystem(entityManager, eventSystem));
		processManager.addProcess(new InvaderStateSystem(entityManager, eventSystem,invaderDestroyedFactory));
		processManager.addProcess(new MotionSystem(entityManager, eventSystem));
		processManager.addProcess(new CollisionSystem<CollisionGroup>(entityManager, eventSystem));
		processManager.addProcess(new ImpactDetectionSystem(entityManager, eventSystem, impactFactory));
		processManager.addProcess(new InvaderImpactSystem(entityManager, eventSystem));
		processManager.addProcess(new AnimationSystem(entityManager, eventSystem));
		processManager.addProcess(new ExplodingTiltSystem(entityManager, eventSystem));
		processManager.addProcess(soundSystem);
		processManager.addProcess(new EntityLifeCycleSystem(entityManager, eventSystem));
		processManager.addProcess(eventSystem);
		
		processManager.init();
		gameWorld.init();
		renderSystem.init();
		GameResources.GAME.MAIN_SONG.play();
	}
	private void updatePhase() {
		int elapsedTime;
		long currentTime = System.nanoTime();
		if(lastTime == 0) elapsedTime = 0;
		else elapsedTime = (int) ((currentTime - lastTime)/1000000);
		lastTime = currentTime;
		
		processManager.update(elapsedTime);
	}
	@Override
	public void render () {
		updatePhase();
		renderSystem.render();
	}
	@Override
	public void dispose () {
	}
}
