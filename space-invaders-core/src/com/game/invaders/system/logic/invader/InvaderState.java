package com.game.invaders.system.logic.invader;

import com.game.invaders.statemachine.State;

public interface InvaderState extends State {
	void hit();
}
