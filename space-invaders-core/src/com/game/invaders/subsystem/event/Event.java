package com.game.invaders.subsystem.event;

public class Event {
	public enum EventType {
		COLLISION,
		INPUT_CONTROL,
		GAME_ACTION,
		ACTOR_CREATED,
		ACTOR_DELETED
	}
	private EventType type;
	public Event(EventType type) {
		super();
		this.type = type;
	}
	public EventType getType() {
		return type;
	}
}
