package com.game.invaders.system.sound;

import com.game.engine.system.event.Event;

public interface SoundResponse {
	Class<? extends Event> reactsTo();
	void handle(Event ev);
}
