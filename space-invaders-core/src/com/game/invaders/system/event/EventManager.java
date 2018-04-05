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
	private List<Event> queue2 = new ArrayList<Event>();
	private boolean processing;
	
	
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
		if(processing)
			queue2.add(e);
		else
			queue.add(e);
	}
	private void swapQueues() {
		if(!queue2.isEmpty()) {
			List<Event> tmp = queue;
			queue = queue2;
			queue2 = tmp;
		}
	}
	private void processEvents() {
		processing = true;
		while(!queue.isEmpty() || !queue2.isEmpty()) {
			for(Event e : queue) {
				for(HandlerRegistration handler_reg : handlers) {
					if(handler_reg.types.contains(e.getType()))
						handler_reg.handler.handle(e);
				}
			}
			queue.clear();
			swapQueues();
		}
		processing = false;
	}
	public void cleanEvents() {
		queue.clear();
		queue2.clear();
	}
	@Override
	public void init() {
		processing = false;
	}
	@Override
	public void update(float dt) {
		processEvents();
	}
}
