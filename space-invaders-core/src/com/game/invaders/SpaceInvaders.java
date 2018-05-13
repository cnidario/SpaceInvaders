package com.game.invaders;

import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.component.Destroyed;
import com.game.engine.entity.component.ShortLife;
import com.game.engine.entity.observer.EntityNotifier;
import com.game.engine.factory.EntityNodeFactory;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.collision.CollisionSystem;
import com.game.engine.system.controller.ControllerSystem;
import com.game.engine.system.input.InputSystem;
import com.game.engine.system.lifecycle.LifecycleSystem;
import com.game.engine.system.motion.MotionSystem;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSetManager;
import com.game.engine.system.node.component.DeleteEntity;
import com.game.engine.system.process.ProcessManager;
import com.game.engine.system.render.AnimationSystem;
import com.game.engine.system.render.RenderSystem;
import com.game.engine.system.util.ComponentMapperSystem;
import com.game.invaders.component.InvaderExplosion;
import com.game.invaders.component.InvaderImpact;
import com.game.invaders.component.ShootEmitted;
import com.game.invaders.data.GameResources;
import com.game.invaders.factory.InvaderFactory;
import com.game.invaders.factory.InvaderGroupFactory;
import com.game.invaders.factory.PlayerShipFactory;
import com.game.invaders.factory.ShootFactory;
import com.game.invaders.system.impact.CollisionGroup;
import com.game.invaders.system.impact.InvaderImpactSystem;
import com.game.invaders.system.invader.ExplodingTiltSystem;
import com.game.invaders.system.invader.InvaderBehaviourSystem;
import com.game.invaders.system.invader.InvaderGroupMovementSystem;
import com.game.invaders.system.invader.InvaderStateSystem;
import com.game.invaders.system.player.PlayerBehaviourSystem;
import com.game.invaders.system.sound.SoundResponseSystem;
import com.game.invaders.system.sound.SoundSystem;

public class SpaceInvaders extends ApplicationAdapter {
	private EntityManager entityManager;
	private ProcessManager processManager;
	private RenderSystem renderSystem;
	private GameWorld gameWorld;
	private long lastTime;
	
	@Override
	public void create () {
		lastTime = 0;
		
		Random rnd = new Random();
		EntityNotifier entityNotifier = new EntityNotifier();
		entityManager = new EntityManager(entityNotifier);
		
		ShootFactory shootFactory = new ShootFactory();
		InvaderFactory invaderFactory = new InvaderFactory();
		InvaderGroupFactory invaderGroupFactory = new InvaderGroupFactory();
		PlayerShipFactory playerShipFactory = new PlayerShipFactory();
		
		EntityNodeFactory entityNodeFactory = new EntityNodeFactory(entityManager);
		NodeSetManager nodeSetManager = new NodeSetManager();
		entityNotifier.attach(nodeSetManager);
		EntityNodeSetFactory entityNodeSetFactory = new EntityNodeSetFactory(entityNodeFactory, nodeSetManager);
		
		Node rootNode = entityNodeFactory.create(entityManager.createEntity());
		
		processManager = new ProcessManager();
		gameWorld = new GameWorld(rootNode, invaderFactory, invaderGroupFactory, playerShipFactory);
		renderSystem = new RenderSystem(entityNodeSetFactory);
		
		SoundSystem soundSystem = new SoundSystem();
		soundSystem.addSoundReponse(new SoundResponseSystem(entityNodeSetFactory, ShootEmitted.class, rnd, GameResources.GAME.SHOOTS));
		soundSystem.addSoundReponse(new SoundResponseSystem(entityNodeSetFactory, InvaderImpact.class, rnd, GameResources.GAME.HITS));
		soundSystem.addSoundReponse(new SoundResponseSystem(entityNodeSetFactory, InvaderExplosion.class, rnd, GameResources.GAME.EXPLOSIONS));
		
		processManager.addProcess(new InputSystem(entityNodeSetFactory, rootNode));
		processManager.addProcess(new ControllerSystem(entityNodeSetFactory));
		processManager.addProcess(new PlayerBehaviourSystem(entityNodeSetFactory, shootFactory));
		processManager.addProcess(new InvaderBehaviourSystem(entityNodeSetFactory));
		processManager.addProcess(new InvaderGroupMovementSystem(entityNodeSetFactory));
		processManager.addProcess(new InvaderStateSystem(entityNodeSetFactory));
		processManager.addProcess(new MotionSystem(entityNodeSetFactory));
		processManager.addProcess(new CollisionSystem<CollisionGroup>(entityNodeSetFactory));
		processManager.addProcess(new InvaderImpactSystem(entityNodeSetFactory));
		processManager.addProcess(new AnimationSystem(entityNodeSetFactory));
		processManager.addProcess(new ExplodingTiltSystem(entityNodeSetFactory));
		processManager.addProcess(soundSystem);
		ComponentMapperSystem componentMapperSystem = new ComponentMapperSystem(entityNodeSetFactory);
		componentMapperSystem.addMapping(ShortLife.class, new DeleteEntity());
		componentMapperSystem.addMapping(Destroyed.class, new DeleteEntity());
		processManager.addProcess(componentMapperSystem);
		processManager.addProcess(new LifecycleSystem(entityNodeSetFactory, entityManager, entityNotifier));
		
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
