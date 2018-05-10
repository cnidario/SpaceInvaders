package com.game.invaders.system.sound;

import java.util.ArrayList;
import java.util.List;
import com.game.engine.system.process.AbstractProcess;

public class SoundSystem extends AbstractProcess {
	private List<SoundResponseSystem> subsystems = new ArrayList<SoundResponseSystem>();
	
	public SoundSystem() {
		super();
	}
	public void addSoundReponse(SoundResponseSystem sr) {
		subsystems.add(sr);
	}
	@Override
	public void update(float dt) {
		for (SoundResponseSystem soundResponseSystem : subsystems) {
			soundResponseSystem.update(dt);
		}
	}
}
