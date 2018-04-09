package com.game.invaders;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.EntityBuilder;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.event.EventSystem;
import com.game.invaders.component.PlayerShip.PlayerState;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;

public class GameWorld {
	private EventSystem eventManager;
	private EntityManager entityManager;
	
	public GameWorld(EventSystem eventManager, EntityManager entityManager) {
		super();
		this.eventManager = eventManager;
		this.entityManager = entityManager;
	}
	public void init() {
		createInvaders();
		createPlayer();
	}
	private void createInvaders() {
		int invaderGroup = EntityBuilder.create()
				.group()
				.position(new Vector2(GameConfigData.INVADER.MINX, 800))
				.motion(new Vector2(GameConfigData.INVADER.MINSPEED, 0))
				.build(entityManager, eventManager);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				EntityBuilder.baseInvader()
					.animation(GameResources.INVADER.INVADERS[i % GameResources.INVADER.INVADERS.length],
							GameConfigData.INVADER.ANIM_TIME, true)
					.groupParent(new Vector2(j*96, -i*76), invaderGroup)
					.build(entityManager, eventManager);
			}
		}
	}
	private int createPlayer() {
		return EntityBuilder.create()
					.position(new Vector2())
					.motion(new Vector2())
					.userControlled()
					.playerShip(PlayerState.STOPPED, GameConfigData.PLAYER.SHOOT_DELAY)
					.renderable(GameResources.PLAYER.PLAYER)
					.build(entityManager, eventManager);
	}
}
