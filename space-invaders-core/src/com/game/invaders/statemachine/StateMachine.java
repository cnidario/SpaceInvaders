package com.game.invaders.statemachine;

import com.game.invaders.scene.actor.Actor;

public interface StateMachine<S extends State> {
	void requestStateChange(S state);
	void update(float dt, Actor actor);
}
