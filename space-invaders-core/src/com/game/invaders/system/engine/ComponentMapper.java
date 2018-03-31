package com.game.invaders.system.engine;

import com.game.invaders.scene.actor.ActorComponent;

public interface ComponentMapper<C extends ActorComponent> {
	boolean present(int entity);
	C get(int entity);
	<T extends C> T present(int entity, Class<T> clazz);
	<T extends C> T get(int entity, Class<T> clazz);
}
