package com.game.invaders.system.process;

public interface Process {
	void kill();
	boolean isAlive();
	void restart();
	void update(float dt);
	void init();
}