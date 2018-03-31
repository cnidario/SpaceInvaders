package com.game.invaders.statemachine;

public interface State {
	void update(float dt, int entity);
	void enter();
	void exit();
}
