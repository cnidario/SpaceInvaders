package com.game.invaders.statemachine;

public interface StateMachine<S extends State> {
	void requestStateChange(S state);
	void update(float dt, int entity);
}
