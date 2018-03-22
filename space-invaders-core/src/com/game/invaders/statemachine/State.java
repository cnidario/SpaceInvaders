package com.game.invaders.statemachine;

import com.game.invaders.actor.Actor;

public interface State {
	void update(float dt, Actor actor);
	void enter();
	void exit();
}
