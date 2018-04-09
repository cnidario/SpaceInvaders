package com.game.engine.system.event.types;

import com.game.engine.system.event.Event;

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
