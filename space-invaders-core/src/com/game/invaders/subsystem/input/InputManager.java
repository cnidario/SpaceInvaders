package com.game.invaders.subsystem.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.invaders.subsystem.event.EventManager;
import com.game.invaders.subsystem.event.types.InputControlEvent;
import com.game.invaders.subsystem.process.AbstractProcess;

public class InputManager extends AbstractProcess {
	private EventManager event_manager;

	public InputManager(EventManager event_manager) {
		super();
		this.event_manager = event_manager;
	}
	private void processInput() {
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
	@Override
	public void init() {
	}
	@Override
	public void update(float dt) {
		processInput();
	}
}
