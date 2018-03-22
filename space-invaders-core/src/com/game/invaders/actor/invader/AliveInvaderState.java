package com.game.invaders.actor.invader;

import com.game.invaders.GameConfigData;
import com.game.invaders.GameResources;
import com.game.invaders.actor.Actor;

public class AliveInvaderState implements InvaderState {
	
	private InvaderStateMachine stateMachine;
	private int speed, xPos;
	public AliveInvaderState(InvaderStateMachine stateMachine) {
		this.stateMachine = stateMachine;
		speed = GameConfigData.INVADER.MINSPEED;
		xPos = 0;
	}
	@Override
	public void hit() {
		stateMachine.requestStateChange(new DyingInvaderState(stateMachine));
	}
	@Override
	public void shoot() {
	}
	@Override
	public void update(float dt, Actor self) {
		xPos += speed*dt;
		if(xPos > GameConfigData.INVADER.DISTANCE_TO_FLIP_DIRECTION) {
			speed = -speed;
			xPos = xPos - (xPos - GameConfigData.INVADER.DISTANCE_TO_FLIP_DIRECTION);
		} else if(xPos < 0) {
			if(speed == GameConfigData.INVADER.MAXSPEED || speed == -GameConfigData.INVADER.MAXSPEED)
				speed = -speed;
			else
				speed = -(speed + GameConfigData.INVADER.SPEEDINCREMENT);
			xPos = -xPos;
			self.getPos().y -= GameConfigData.INVADER.DESCEND_DISTANCE + GameResources.INVADER.IMAGE.getHeight();
		}
		self.getPos().x += xPos;
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
