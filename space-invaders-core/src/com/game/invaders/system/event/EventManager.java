package com.game.invaders.system.event;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import com.game.invaders.system.event.Event.EventType;
import com.game.invaders.system.process.AbstractProcess;

public class EventManager extends AbstractProcess {
	public interface EventListener {
		void handle(Event e);
	}
	private class HandlerRegistration {
		EventListener handler;
		EnumSet<EventType> types;
		public HandlerRegistration(EventListener handler, EnumSet<EventType> types) {
			this.handler = handler;
			this.types = types;
		}
	}
	
	private List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();
	private List<Event> queue = new ArrayList<Event>();
	
	public void registerHandler(EventListener handler, EnumSet<EventType> types) {
		handlers.add(new HandlerRegistration(handler, types));
	}
	public void removeHandler(EventListener handler) {
		HandlerRegistration found = null;
		for(HandlerRegistration handler_reg : handlers) {
			if(handler_reg.handler == handler) {
				found = handler_reg;
				break;
			}
		}
		if(found != null)
			handlers.remove(found);
	}
	public void queueEvent(Event e) {
		queue.add(e);
	}
	private void processEvents() {
		for(Event e : queue) {
			for(HandlerRegistration handler_reg : handlers) {
				if(handler_reg.types.contains(e.getType()))
					handler_reg.handler.handle(e);
			}
		}
	}
	public void cleanEvents() {
		queue.clear();
	}
	@Override
	public void init() {
	}
	@Override
	public void update(float dt) {
		processEvents();
	}
}
