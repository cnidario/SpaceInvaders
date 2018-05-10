package com.game.invaders;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.EntityManager;
import com.game.invaders.data.GameResources;
import com.game.invaders.factory.InvaderFactory;
import com.game.invaders.factory.InvaderGroupFactory;
import com.game.invaders.factory.PlayerShipFactory;

public class GameWorld {
	private EntityManager entityManager;
	private InvaderFactory invaderFactory;
	private InvaderGroupFactory invaderGroupFactory;
	private PlayerShipFactory playerShipFactory;
	
	public GameWorld(EntityManager entityManager, InvaderFactory invaderFactory, InvaderGroupFactory invaderGroupFactory, PlayerShipFactory playerShipFactory) {
		super();
		this.entityManager = entityManager;
		this.invaderFactory = invaderFactory;
		this.invaderGroupFactory = invaderGroupFactory;
		this.playerShipFactory = playerShipFactory;
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
		return playerShipFactory.create();
	}
}
