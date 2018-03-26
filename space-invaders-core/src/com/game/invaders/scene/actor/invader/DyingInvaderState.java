package com.game.invaders.scene.actor.invader;

import com.game.invaders.scene.actor.Actor;

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
	public void update(float dt, Actor actor) {
		// TODO Auto-generated method stub
	}
	@Override
	public void enter() {
		// TODO Auto-generated method stub
	}
	@Override
	public void exit() {
		// TODO Auto-generated method stub
	}
}
