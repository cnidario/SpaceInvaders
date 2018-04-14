package com.game.invaders.factory;

import com.game.engine.system.event.EventSystem;
import com.game.invaders.event.InvaderDestroyedEvent;

public class InvaderDestroyedEventFactory {
	private EventSystem eventManager;

	public InvaderDestroyedEventFactory(EventSystem eventManager) {
		super();
		this.eventManager = eventManager;
	}
	public void create(int invader) {
		eventManager.queueEvent(new InvaderDestroyedEvent(invader));
	}
}
