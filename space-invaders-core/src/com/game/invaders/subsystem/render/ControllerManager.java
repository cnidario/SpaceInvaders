package com.game.invaders.subsystem.render;

import java.util.EnumSet;

import com.game.invaders.scene.actor.Actor;
import com.game.invaders.subsystem.event.Event;
import com.game.invaders.subsystem.event.EventManager;
import com.game.invaders.subsystem.event.EventManager.EventListener;

public class ControllerManager implements EventListener {
	private EventManager event_manager;
	private Actor controlled;
	public ControllerManager(EventManager event_manager, Actor controlled) { //Actor controlled = sucio FIXME
		this.event_manager = event_manager;
		this.controlled = controlled;
	}
	public void init() {
		event_manager.registerHandler(this, EnumSet.of(Event.EventType.INPUT_CONTROL));
	}
	@Override
	public void handle(Event e) {
	}
}
