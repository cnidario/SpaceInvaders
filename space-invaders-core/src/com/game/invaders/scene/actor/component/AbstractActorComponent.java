package com.game.invaders.scene.actor.component;

import com.game.invaders.scene.actor.ActorComponent;

public abstract class AbstractActorComponent extends ActorComponent {
	private final ActorComponentID type;
	public AbstractActorComponent(ActorComponentID type) {
		this.type = type;
	}
	@Override
	public ActorComponentID getID() { return type; }
}
