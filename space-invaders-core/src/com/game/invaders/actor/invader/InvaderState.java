package com.game.invaders.actor.invader;

import com.game.invaders.statemachine.State;

public interface InvaderState extends State {
	void hit();
	void shoot();
}
