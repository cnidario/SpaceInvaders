package com.game.invaders.actor.invader;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.invaders.animation.Animation;
import com.game.invaders.animation.AnimationPlayer;
import com.game.invaders.subsystem.collision.BoundingBox;

public class AliveInvaderState implements InvaderState {
	private final static Texture image =  new Texture("invader1.png"); 
	private final static TextureRegion[] invaders = { new TextureRegion(image, 64, 64), new TextureRegion(image, 64, 0, 64, 64)};
	private final static Animation MOVING_ANIM = new Animation(Arrays.asList(invaders), true);
	//uso una animación para todos, la misma, podría usar una independiente y que no se movieran sincronizados
	public final static AnimationPlayer MOVING_ANIM_PLAYER = new AnimationPlayer(MOVING_ANIM, 1200);
	
	private final static int MINSPEED = 100;
	private final static int MAXSPEED = 400;
	private final static int SPEEDINCREMENT = 100;
	private final static int DISTANCE_TO_FLIP_DIRECTION = 125;
	private final static int DESCEND_DISTANCE = 50; 
	
	public final static BoundingBox BBOX = new BoundingBox(image.getWidth(), image.getHeight());
	
	private InvaderStateMachine stateMachine;
	private int speed, xPos;
	public AliveInvaderState(InvaderStateMachine stateMachine) {
		this.stateMachine = stateMachine;
		speed = MINSPEED;
		xPos = 0;
	}
	@Override
	public void hit() {
		stateMachine.requestChangeState(new DyingInvaderState(stateMachine));
	}
	@Override
	public void shoot() {
	}
	@Override
	public AnimationPlayer getAnimation() { return MOVING_ANIM_PLAYER; }
	@Override
	public void update(float dt, Invader self) {
		xPos += speed*dt;
		if(xPos > DISTANCE_TO_FLIP_DIRECTION) {
			speed = -speed;
			xPos = xPos - (xPos - DISTANCE_TO_FLIP_DIRECTION);
		} else if(xPos < 0) {
			if(speed == MAXSPEED || speed == -MAXSPEED)
				speed = -speed;
			else
				speed = -(speed + SPEEDINCREMENT);
			xPos = -xPos;
			self.getPos().y -= DESCEND_DISTANCE + image.getHeight();
		}
		self.getPos().x += xPos;
	}
}
