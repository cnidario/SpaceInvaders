package com.game.invaders.subsystem.event;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.game.invaders.subsystem.event.Event.EventType;

public class EventManager {
	public interface EventListener {
		void handle(Event e);
	}
	class HandlerRegistration {
		EventListener handler;
		EnumSet<EventType> types;
		public HandlerRegistration(EventListener handler, EnumSet<EventType> types) {
			this.handler = handler;
			this.types = types;
		}
	}
	
	private List<HandlerRegistration> handlers = new ArrayList<HandlerRegistration>();
	private List<Event> queue = new ArrayList<Event>();
	
	public EventManager() {
	}
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
	public void processEvents() {
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
	public void init() {
	}
}
