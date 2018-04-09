package com.game.engine.statemachine;

public interface StateMachine<S extends State> {
	void requestStateChange(S state);
	void update(float dt, int entity);
}
