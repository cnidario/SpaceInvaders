package com.game.invaders.factory;

import com.game.engine.system.event.EventSystem;
import com.game.invaders.event.InvaderImpactedEvent;

public class ImpactEventFactory {
	private EventSystem eventManager;
	
	public ImpactEventFactory(EventSystem eventManager) {
		super();
		this.eventManager = eventManager;
	}
	public void create(int invader, int shoot) {
		eventManager.queueEvent(new InvaderImpactedEvent(invader, shoot));
	}
}
