package com.game.invaders;

import java.util.ArrayList;
import java.util.List;
import com.game.invaders.actor.Actor;
import com.game.invaders.actor.components.StateMachineActorC;
import com.game.invaders.actor.invader.InvaderState;
import com.game.invaders.actor.invader.InvaderStateMachine;
import com.game.invaders.actor.player.Player;
import com.game.invaders.subsystem.event.Event;
import com.game.invaders.subsystem.event.EventManager;
import com.game.invaders.subsystem.event.types.ActorLifeCycleEvent;

public class GameWorld {
	private List<Actor> actors = new ArrayList<Actor>();
	private Player player;
	private EventManager event_manager;
	
	public GameWorld(EventManager event_manager) {
		this.event_manager = event_manager;
	}
	
	public void create() {
		createInvaders();
		actors.add(player);
	}
	
	private void createInvaders() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				Actor invader = createInvader(j*96, -i*76);
				actors.add(invader);
				event_manager.queueEvent(new ActorLifeCycleEvent(Event.EventType.ACTOR_CREATED, invader));
			}
		}
	}
	private Actor createInvader(float x, float y) {
		Actor invader = new Actor();
		invader.addComponent(GameResources.INVADER.RENDER_COMPO);
		invader.addComponent(GameResources.INVADER.COLLISION_COMPO);
		InvaderStateMachine stm = new InvaderStateMachine();
		invader.addComponent(new StateMachineActorC<InvaderState>(stm));
		invader.getPos().x = x;
		invader.getPos().y = y;
		return invader;
	}
	private Actor createPlayer() {
		Actor player = new Actor();
		player.addComponent(GameResources.PLAYER.RENDER_COMPO);
		return player;
	}
	
	public void update(float dt) {
		
	}
	public void init() {
	}
}
