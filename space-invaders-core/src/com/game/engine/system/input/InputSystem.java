package com.game.engine.system.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.types.InputControlEvent;
import com.game.engine.system.process.AbstractProcess;

public class InputSystem extends AbstractProcess {
	private EventSystem event_manager;

	public InputSystem(EventSystem event_manager) {
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
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
			event_manager.queueEvent(new InputControlEvent(InputControlEvent.InputControlType.SHOOT));
	}
	@Override
	public void init() {
	}
	@Override
	public void update(float dt) {
		processInput();
	}
}
