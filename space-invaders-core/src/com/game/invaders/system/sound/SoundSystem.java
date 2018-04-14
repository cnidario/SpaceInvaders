package com.game.invaders.system.sound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.game.engine.system.event.Event;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.EventSystem.EventListener;
import com.game.engine.system.process.AbstractProcess;

public class SoundSystem extends AbstractProcess {
	private EventSystem eventManager;
	private Map<Class<? extends Event>, List<SoundResponse>> mappings;
	
	public SoundSystem(EventSystem eventManager) {
		super();
		this.eventManager = eventManager;
		mappings = new HashMap<Class<? extends Event>, List<SoundResponse>>();
	}
	public void addSoundReponse(SoundResponse sr) {
		Class<? extends Event> clazz = sr.reactsTo();
		List<SoundResponse> srlist = mappings.get(clazz);
		if(srlist == null) {
			srlist = new ArrayList<SoundResponse>();
			mappings.put(clazz, srlist);
		}
		srlist.add(sr);
	}
	@SuppressWarnings("rawtypes")
	private class DynamicEventListener implements EventListener {
		private List<SoundResponse> srs;
		
		public DynamicEventListener(List<SoundResponse> srs) {
			super();
			this.srs = srs;
		}
		@Override
		public void handle(Event e) {
			for (SoundResponse soundResponse : srs) {
				soundResponse.handle(e);
			}
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		for (Class<? extends Event> clazz : mappings.keySet()) {
			eventManager.registerHandler(new DynamicEventListener(mappings.get(clazz)), clazz);
		}
	}
	@Override
	public void update(float dt) {
	}
}
