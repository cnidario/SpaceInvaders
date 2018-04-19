package com.game.invaders.factory;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.factory.EntityBuilderFactory;

public class InvaderImpactFactory {
	private EntityBuilderFactory entityBuilderFactory;
	
	public InvaderImpactFactory(EntityBuilderFactory entityBuilderFactory) {
		super();
		this.entityBuilderFactory = entityBuilderFactory;
	}
	public int create(Vector2 pos, int invader, float time) {
		return entityBuilderFactory.create()
			.position(pos)
			.invaderImpact(invader, time)
			.build();
	}
}
