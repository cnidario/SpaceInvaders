package com.game.invaders.system.invader;

import com.game.engine.statemachine.State;

public interface InvaderState extends State {
	void hit();
}
