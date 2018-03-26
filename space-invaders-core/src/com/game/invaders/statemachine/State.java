package com.game.invaders.statemachine;

import com.game.invaders.scene.actor.Actor;

public interface State {
	void update(float dt, Actor actor);
	void enter();
	void exit();
}
