package com.game.invaders.system.event.types;

import com.game.invaders.scene.actor.Actor;
import com.game.invaders.system.event.Event;

public class ActorLifeCycleEvent extends Event {
	private Actor actor;
	public ActorLifeCycleEvent(EventType type, Actor actor) {
		super(type);
		this.actor = actor;
	}
	public Actor getActor() {
		return actor;
	}
}
