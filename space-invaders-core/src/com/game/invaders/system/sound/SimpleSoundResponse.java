package com.game.invaders.system.sound;

import java.util.Random;

import com.badlogic.gdx.audio.Sound;
import com.game.engine.system.event.Event;

public class SimpleSoundResponse implements SoundResponse {
	private Random rnd;
	private Sound[] clips;
	private Class<? extends Event> eventType;
	
	
	public SimpleSoundResponse(Random rnd, Sound[] clips, Class<? extends Event> eventType) {
		super();
		this.rnd = rnd;
		this.clips = clips;
		this.eventType = eventType;
	}
	@Override
	public Class<? extends Event> reactsTo() {
		return eventType;
	}

	@Override
	public void handle(Event ev) {
		clips[rnd.nextInt(clips.length)].play(1f);
	}
	
}
