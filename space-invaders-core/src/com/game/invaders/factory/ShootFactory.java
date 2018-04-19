package com.game.invaders.factory;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.factory.EntityBuilderFactory;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;
import com.game.invaders.system.impact.CollisionGroup;

public class ShootFactory {
	private EntityBuilderFactory entityBuilderFactory;
	
	public ShootFactory(EntityBuilderFactory entityBuilderFactory) {
		super();
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
				.build();
		//XXX eventSystem.queueEvent(new ShootCreatedEvent(shoot));
		return shoot;
	}
}
