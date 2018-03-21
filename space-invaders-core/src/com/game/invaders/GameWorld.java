package com.game.invaders;

import java.util.ArrayList;
import java.util.List;

import com.game.invaders.actor.Actor;
import com.game.invaders.actor.invader.Invader;
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
				Invader invader = buildInvader(j*96, -i*76);
				actors.add(invader);
				event_manager.queueEvent(new ActorLifeCycleEvent(Event.EventType.ACTOR_CREATED, invader));
			}
		}
	}
	private Invader buildInvader(float x, float y) {
		Invader obj = new Invader(x, y);
		return obj;
	}
	
	public void update(float dt) {
		
	}
	public void init() {
	}
}
