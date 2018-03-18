package com.game.invaders.process;

public interface Process {
	void kill();
	boolean isAlive();
	void restart();
	void update(float dt);
}