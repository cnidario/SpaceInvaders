package com.game.invaders.subsystem.event.types;

import com.game.invaders.actor.Actor;
import com.game.invaders.subsystem.event.Event;

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
