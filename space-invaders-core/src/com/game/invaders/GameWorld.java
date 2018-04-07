package com.game.invaders;

import com.badlogic.gdx.math.Vector2;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.scene.actor.InvaderBuilder;
import com.game.invaders.scene.actor.component.ControllerC;
import com.game.invaders.scene.actor.component.GroupC;
import com.game.invaders.scene.actor.component.PhysicsActorC;
import com.game.invaders.scene.actor.component.PlayerShipStateC;
import com.game.invaders.scene.actor.component.PositionActorC;
import com.game.invaders.scene.actor.component.RenderableActorC;
import com.game.invaders.scene.actor.component.PlayerShipStateC.PlayerState;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.types.ActorLifeCycleEvent;

public class GameWorld {
	private EventManager eventManager;
	private EntityManager entityManager;
	
	public GameWorld(EventManager eventManager, EntityManager entityManager) {
		super();
		this.eventManager = eventManager;
		this.entityManager = entityManager;
	}
	public void init() {
		createInvaders();
		createPlayer();
	}
	private void createInvaders() {
		int invaderGroup = entityManager.createEntity();
		entityManager.addComponent(invaderGroup, new GroupC());
		entityManager.addComponent(invaderGroup, new PositionActorC(new Vector2(GameConfigData.INVADER.MINX, 800)));
		entityManager.addComponent(invaderGroup, new PhysicsActorC(new Vector2(GameConfigData.INVADER.MINSPEED, 0)));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				InvaderBuilder.create()
					.num(i % GameResources.INVADER.INVADERS.length)
					.intoGroup(new Vector2(j*96, -i*76), invaderGroup)
					.build(entityManager, eventManager);
			}
		}
	}
	private int createPlayer() {
		int player = entityManager.createEntity();
		entityManager.addComponent(player, new PositionActorC(new Vector2()));
		entityManager.addComponent(player, new PhysicsActorC(new Vector2()));
		entityManager.addComponent(player, new ControllerC());
		entityManager.addComponent(player, new PlayerShipStateC(PlayerState.STOPPED, GameConfigData.PLAYER.SHOOT_DELAY));
		entityManager.addComponent(player, new RenderableActorC(GameResources.PLAYER.PLAYER));
		eventManager.queueEvent(new ActorLifeCycleEvent(Event.EventType.ACTOR_CREATED, player));
		return player;
	}
}
