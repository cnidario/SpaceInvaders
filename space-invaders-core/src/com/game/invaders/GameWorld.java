package com.game.invaders;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.scene.actor.components.ChildOfGroupC;
import com.game.invaders.scene.actor.components.CollisionActorC;
import com.game.invaders.scene.actor.components.ControllerC;
import com.game.invaders.scene.actor.components.RenderableActorC;
import com.game.invaders.scene.actor.components.CollisionActorC.CollisionGroup;
import com.game.invaders.scene.actor.components.GroupC;
import com.game.invaders.scene.actor.components.InvaderStateC;
import com.game.invaders.scene.actor.components.InvaderStateC.InvaderStateID;
import com.game.invaders.scene.actor.components.PhysicsActorC;
import com.game.invaders.scene.actor.components.PlayerShipStateC;
import com.game.invaders.scene.actor.components.PlayerShipStateC.PlayerState;
import com.game.invaders.scene.actor.components.PositionActorC;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.types.ActorLifeCycleEvent;

public class GameWorld {
	private EventManager eventManager;
	private EntityManager entityManager;
	
	public GameWorld(EventManager eventManager, EntityManager entityManager) {
		super();
		this.eventManager = eventManager;
		this.entityManager = entityManager;
	}
	public void init() {
		createInvaders();
		createPlayer();
	}
	private void createInvaders() {
		int invaderGroup = entityManager.createEntity();
		entityManager.addComponent(invaderGroup, new GroupC());
		entityManager.addComponent(invaderGroup, new PositionActorC(new Vector2(GameConfigData.INVADER.MINX, 800)));
		entityManager.addComponent(invaderGroup, new PhysicsActorC(new Vector2(GameConfigData.INVADER.MINSPEED, 0)));
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				int invader = createInvader();
				entityManager.addComponent(invader, new ChildOfGroupC(new Vector2(j*96, -i*76), invaderGroup));
				eventManager.queueEvent(new ActorLifeCycleEvent(Event.EventType.ACTOR_CREATED, invader));
			}
		}
	}
	private int createInvader() {
		int invader = entityManager.createEntity();
		entityManager.addComponent(invader, new RenderableActorC(GameResources.INVADER.INVADERS[0]));
		entityManager.addComponent(invader, 
				new CollisionActorC(GameResources.INVADER.BBOX, 
						EnumSet.of(CollisionGroup.PLAYER_SHOOT), EnumSet.of(CollisionGroup.INVADER)));
		entityManager.addComponent(invader, new PositionActorC(new Vector2()));
		//entityManager.addComponent(invader, new PhysicsActorC(new Vector2()));
		entityManager.addComponent(invader, new InvaderStateC(InvaderStateID.ALIVE));
		return invader;
	}
	private int createPlayer() {
		int player = entityManager.createEntity();
		entityManager.addComponent(player, new PositionActorC(new Vector2()));
		entityManager.addComponent(player, new PhysicsActorC(new Vector2()));
		entityManager.addComponent(player, new ControllerC());
		entityManager.addComponent(player, new PlayerShipStateC(PlayerState.STOPPED));
		entityManager.addComponent(player, new RenderableActorC(GameResources.PLAYER.PLAYER));
		eventManager.queueEvent(new ActorLifeCycleEvent(Event.EventType.ACTOR_CREATED, player));
		return player;
	}
}
