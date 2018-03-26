package com.game.invaders.subsystem.controller;

import java.util.EnumSet;
import com.game.invaders.scene.actor.Actor;
import com.game.invaders.subsystem.event.Event;
import com.game.invaders.subsystem.event.EventManager;
import com.game.invaders.subsystem.event.EventManager.EventListener;
import com.game.invaders.subsystem.process.AbstractProcess;

public class ControllerManager extends AbstractProcess implements EventListener {
	private EventManager event_manager;
	private Actor controlled;
	public ControllerManager(EventManager event_manager, Actor controlled) { //Actor controlled = sucio FIXME
		this.event_manager = event_manager;
		this.controlled = controlled;
	}
	@Override
	public void handle(Event e) {
	}
	@Override
	public void init() {
		event_manager.registerHandler(this, EnumSet.of(Event.EventType.INPUT_CONTROL));
	}
	@Override
	public void update(float dt) {
	}
}
