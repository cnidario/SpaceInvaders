package com.game.invaders.actor.invader;

import com.game.invaders.animation.AnimationPlayer;

public class DyingInvaderState implements InvaderState {
	private InvaderStateMachine stateMachine;
	public DyingInvaderState(InvaderStateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}
	@Override
	public void hit() {
		throw new UnsupportedOperationException("hit registrado en invader que se muere");
	}
	@Override
	public void shoot() {
		throw new UnsupportedOperationException("shoot registrado en invader que se muere");
	}
	@Override
	public AnimationPlayer getAnimation() { return AliveInvaderState.MOVING_ANIM_PLAYER; }
	@Override
	public void update(float dt, Invader self) {
		//FIXME
	}
}
