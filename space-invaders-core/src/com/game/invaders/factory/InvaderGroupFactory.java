package com.game.invaders.factory;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.factory.EntityBuilderFactory;
import com.game.invaders.data.GameConfigData;

public class InvaderGroupFactory {
	private EntityBuilderFactory entityBuilderFactory;
	public InvaderGroupFactory(EntityBuilderFactory entityBuilderFactory) {
		super();
		this.entityBuilderFactory = entityBuilderFactory;
	}
	public int create() {
		return entityBuilderFactory.create()
				.group()
				.position(new Vector2(GameConfigData.INVADER.MINX, 800))
				.motion(new Vector2(GameConfigData.INVADER.MINSPEED, 0))
				.build();
	}
}
