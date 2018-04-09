package com.game.engine.system.event.types;

import com.game.engine.system.event.Event;

public class InputControlEvent extends Event {
	public enum InputControlType {
		STOP_MOVE,
		MOVE_LEFT,
		MOVE_RIGHT,
		SHOOT
	}
	
	private InputControlType controlType;
	public InputControlEvent(InputControlType controlType) {
		super(Event.EventType.INPUT_CONTROL);
		this.controlType = controlType;
	}
	public InputControlType getControlType() {
		return controlType;
	}
}
