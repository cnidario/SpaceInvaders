package com.game.invaders.factory;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.event.EventSystem;
import com.game.invaders.component.PlayerShip.PlayerState;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;

public class PlayerShipFactory {
	private EntityManager entityManager;
	private EventSystem eventSystem;
	private EntityBuilderFactory entityBuilderFactory;
	
	public PlayerShipFactory(EntityManager entityManager, EventSystem eventSystem, EntityBuilderFactory entityBuilderFactory) {
		super();
		this.entityManager = entityManager;
		this.eventSystem = eventSystem;
		this.entityBuilderFactory = entityBuilderFactory;
	}
	public int create() {
		return entityBuilderFactory.create()
				.position(new Vector2())
				.motion(new Vector2())
				.userControlled()
				.playerShip(PlayerState.STOPPED, GameConfigData.PLAYER.SHOOT_DELAY)
				.renderable(GameResources.PLAYER.PLAYER)
				.build(entityManager, eventSystem);
	}
}