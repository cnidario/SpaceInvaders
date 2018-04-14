package com.game.engine.system.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.game.engine.system.process.AbstractProcess;

public class EventSystem extends AbstractProcess {
	public interface EventListener<T extends Event> {
		void handle(T e);
	}
	
	@SuppressWarnings("rawtypes")
	private Map<Class<? extends Event>, List<EventListener>> handlers;
	private List<Event> queue;
	
	@SuppressWarnings("rawtypes")
	public EventSystem() {
		this.handlers = new HashMap<Class<? extends Event>, List<EventListener>>();
		this.queue = new ArrayList<Event>();
	}
	@SuppressWarnings("rawtypes")
	public <T extends Event> void registerHandler(EventListener<T> handler, Class<T> clazz) {
		List<EventListener> listeners = handlers.get(clazz);
		if(listeners == null)
			listeners = new ArrayList<EventSystem.EventListener>();
		listeners.add(handler);
		handlers.put(clazz, listeners);
	}
	@SuppressWarnings("rawtypes")
	public <T extends Event> void removeHandler(EventListener<T> handler, Class<T> clazz) {
		List<EventListener> listeners = handlers.get(clazz);
		if(listeners != null)
			listeners.remove(handler);
	}
	public void queueEvent(Event e) {
		queue.add(e);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void processEvents() {
		List<Event> queueCopy = new ArrayList<Event>(queue);
		queue = new ArrayList<Event>();
		for(Event e : queueCopy) {
			List<EventListener> listeners = handlers.get(e.getClass());
			if(listeners != null)
				for(EventListener listener : listeners) {
					listener.handle(e);
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
