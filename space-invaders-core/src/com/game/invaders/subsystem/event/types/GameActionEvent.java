package com.game.invaders.subsystem.event.types;

import com.game.invaders.subsystem.event.Event;

public class GameActionEvent extends Event {
	public enum GameActionEventType {
		
	}
	
	private GameActionEventType gameActionType;
	public GameActionEvent(GameActionEventType gameActionType) {
		super(Event.EventType.GAME_ACTION);
		this.gameActionType = gameActionType;
	}
	public GameActionEventType getGameActionType() {
		return gameActionType;
	}
}
