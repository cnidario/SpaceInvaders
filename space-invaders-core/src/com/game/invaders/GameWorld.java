package com.game.invaders;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.event.EventSystem;
import com.game.invaders.component.PlayerShip.PlayerState;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;
import com.game.invaders.factory.InvaderFactory;
import com.game.invaders.factory.InvaderGroupFactory;

public class GameWorld {
	private EventSystem eventManager;
	private EntityManager entityManager;
	private InvaderFactory invaderFactory;
	private InvaderGroupFactory invaderGroupFactory;
	
	public GameWorld(EventSystem eventManager, EntityManager entityManager, InvaderFactory invaderFactory, InvaderGroupFactory invaderGroupFactory) {
		super();
		this.eventManager = eventManager;
		this.entityManager = entityManager;
		this.invaderFactory = invaderFactory;
		this.invaderGroupFactory = invaderGroupFactory;
	}
	public void init() {
		createInvaders();
		createPlayer();
	}
	private void createInvaders() {
		int invaderGroup = invaderGroupFactory.create();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				int type = i % GameResources.INVADER.INVADERS.length;
				invaderFactory.create(new Vector2(j*96, -i*76), type, invaderGroup);
			}
		}
	}
	private int createPlayer() {
		return new EntityBuilder()
					.position(new Vector2())
					.motion(new Vector2())
					.userControlled()
					.playerShip(PlayerState.STOPPED, GameConfigData.PLAYER.SHOOT_DELAY)
					.renderable(GameResources.PLAYER.PLAYER)
					.build(entityManager, eventManager);
	}
}
