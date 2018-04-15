package com.game.invaders.factory;

import com.game.invaders.EntityBuilder;

public class EntityBuilderFactory {
	public EntityBuilder create() {
		return new EntityBuilder();
	}
}
