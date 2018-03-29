package com.game.invaders.scene.actor.components;

import com.game.invaders.scene.actor.ActorComponent;

public abstract class AbstractActorComponent implements ActorComponent {
	private final ActorComponentID type;
	public AbstractActorComponent(ActorComponentID type) {
		this.type = type;
	}
	@Override
	public ActorComponentID getID() { return type; }
}
