package com.game.invaders.actor.invader;

import com.game.invaders.animation.AnimationPlayer;

public interface InvaderState {
	void hit();
	void shoot();
	void update(float dt, Invader self);
	
	AnimationPlayer getAnimation();
}
