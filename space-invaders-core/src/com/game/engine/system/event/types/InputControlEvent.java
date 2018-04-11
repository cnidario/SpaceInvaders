package com.game.engine.system.event.types;

import com.game.engine.system.event.Event;

public class InputControlEvent implements Event {
	public enum InputControlType {
		STOP_MOVE,
		MOVE_LEFT,
		MOVE_RIGHT,
		SHOOT
	}
	
	private InputControlType controlType;
	
	public InputControlEvent(InputControlType controlType) {
		this.controlType = controlType;
	}
	public InputControlType getControlType() {
		return controlType;
	}
}
