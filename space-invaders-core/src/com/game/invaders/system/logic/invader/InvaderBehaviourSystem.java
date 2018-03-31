package com.game.invaders.system.logic.invader;

import com.badlogic.gdx.math.Vector2;
import com.game.invaders.GameConfigData;
import com.game.invaders.GameResources;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.scene.actor.ActorComponent;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.components.PhysicsActorC;
import com.game.invaders.scene.actor.components.PositionActorC;
import com.game.invaders.statemachine.StateMachine;
import com.game.invaders.system.process.AbstractProcess;

public class InvaderBehaviourSystem extends AbstractProcess implements StateMachine<InvaderState> {
	public enum InvaderStateID {
		ALIVE, DYING, DIED
	}
	
	public class InvaderAliveState implements InvaderState {
		private InvaderBehaviourSystem self;
		
		public InvaderAliveState(InvaderBehaviourSystem self) {
			super();
			this.self = self;
		}
		private ActorComponent retrieveComponent(ActorComponentID cid) {
			return self.getEntityManager().componentFor(self.getInvader(), cid);
		}
		@Override
		public void update(float dt, int entity) {
			PhysicsActorC physics_comp = (PhysicsActorC) retrieveComponent(ActorComponentID.PHYSICS);
			PositionActorC position_comp = (PositionActorC) retrieveComponent(ActorComponentID.POSITION);
			Vector2 speed = physics_comp.getSpeed();
			Vector2 pos = position_comp.getPos();
			if(pos.x > GameConfigData.INVADER.DISTANCE_TO_FLIP_DIRECTION) {
				speed.x = -speed.x;
				pos.x = pos.x - (pos.x - GameConfigData.INVADER.DISTANCE_TO_FLIP_DIRECTION);
			} else if(pos.x < 0) {
				if(speed.x == GameConfigData.INVADER.MAXSPEED || speed.x == -GameConfigData.INVADER.MAXSPEED)
					speed.x = -speed.x;
				else
					speed.x = -(speed.x + GameConfigData.INVADER.SPEEDINCREMENT);
				pos.x = -pos.x;
				pos.y -= GameConfigData.INVADER.DESCEND_DISTANCE + GameResources.INVADER.IMAGE.getHeight();
			}
		}
		@Override
		public void enter() {
		}
		@Override
		public void exit() {
		}
		@Override
		public void hit() {
			self.requestStateChange(new InvaderDyingState(self));
		}
	}
	public class InvaderDyingState implements InvaderState {
		private InvaderBehaviourSystem self;
		private float time;
		
		public InvaderDyingState(InvaderBehaviourSystem self) {
			super();
			this.self = self;
		}
		@Override
		public void update(float dt, int entity) {
			time -= dt;
			if(time < 0)
				self.requestStateChange(this);
		}
		@Override
		public void enter() {
			time = 350;
		}
		@Override
		public void exit() {
		}
		@Override
		public void hit() {
		}
	}
	
	public class InvaderDiedState implements InvaderState {
		private InvaderBehaviourSystem self;
		
		public InvaderDiedState(InvaderBehaviourSystem self) {
			super();
			this.self = self;
		}
		@Override
		public void update(float dt, int entity) {
		}
		@Override
		public void enter() {
		}
		@Override
		public void exit() {
		}
		@Override
		public void hit() {
		}
	}
	
	private EntityManager entityManager;
	private InvaderState state = new InvaderAliveState(this);
	private int invader;
	
	
	public InvaderBehaviourSystem(int invader, EntityManager entityManager) {
		super();
		this.invader = invader;
		this.entityManager = entityManager;
	}
	public InvaderState getState() {
		return state;
	}
	public void setState(InvaderState state) {
		this.state = state;
	}
	public EntityManager getEntityManager() {
		return entityManager;
	}
	public int getInvader() {
		return invader;
	}
	@Override
	public void update(float dt) {
		update(dt, invader);
	}
	@Override
	public void requestStateChange(InvaderState state) {
		setState(state);
	}
	@Override
	public void update(float dt, int entity) {
		state.update(dt, entity);
	}
}
