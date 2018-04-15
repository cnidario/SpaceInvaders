package com.game.invaders.factory;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.event.EventSystem;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;
import com.game.invaders.event.ShootCreatedEvent;
import com.game.invaders.system.impact.CollisionGroup;

public class ShootFactory {
	private EntityManager manager;
	private EventSystem eventSystem;
	private EntityBuilderFactory entityBuilderFactory;
	
	public ShootFactory(EntityManager manager, EventSystem eventSystem, EntityBuilderFactory entityBuilderFactory) {
		super();
		this.manager = manager;
		this.eventSystem = eventSystem;
		this.entityBuilderFactory = entityBuilderFactory;
	}
	public int create(Vector2 pos) {
		int shoot = entityBuilderFactory.create()
				.position(pos)
				.motion(new Vector2(0, GameConfigData.PLAYER.SHOOT_SPEED))
				.collision(GameResources.PLAYER.SHOOT_BBOX,
						EnumSet.of(CollisionGroup.INVADER),
						EnumSet.of(CollisionGroup.PLAYER_SHOOT))
				.renderable(GameResources.PLAYER.SHOOT)
				.build(manager, eventSystem);
		eventSystem.queueEvent(new ShootCreatedEvent(shoot));
		return shoot;
	}
}
