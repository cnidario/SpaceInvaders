package com.game.invaders.subsystem.process;

public interface Process {
	void kill();
	boolean isAlive();
	void restart();
	void update(float dt);
	void init();
}