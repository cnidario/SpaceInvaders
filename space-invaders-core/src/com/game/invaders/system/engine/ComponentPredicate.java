package com.game.invaders.system.engine;

import java.util.Set;

import com.game.invaders.scene.actor.ActorComponent;

public interface ComponentPredicate {
	boolean match(Set<ActorComponent> components);
}
