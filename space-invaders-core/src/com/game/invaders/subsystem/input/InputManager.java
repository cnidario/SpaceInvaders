package com.game.invaders.subsystem.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.invaders.subsystem.event.EventManager;
import com.game.invaders.subsystem.event.types.InputControlEvent;

public class InputManager {
	private EventManager event_manager;

	public InputManager(EventManager event_manager) {
		super();
		this.event_manager = event_manager;
	}
	
	public void processInput() {
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			event_manager.queueEvent(new InputControlEvent(InputControlEvent.InputControlType.MOVE_LEFT));
		} else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			event_manager.queueEvent(new InputControlEvent(InputControlEvent.InputControlType.MOVE_RIGHT));
		} else {
			event_manager.queueEvent(new InputControlEvent(InputControlEvent.InputControlType.STOP_MOVE));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			event_manager.queueEvent(new InputControlEvent(InputControlEvent.InputControlType.SHOOT));
		}
	}

	public void init() {
	}
}
