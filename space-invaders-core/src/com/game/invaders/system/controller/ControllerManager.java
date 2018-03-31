package com.game.invaders.system.controller;

import java.util.EnumSet;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.scene.actor.components.PlayerShipStateC;
import com.game.invaders.scene.actor.components.PlayerShipStateC.PlayerState;
import com.game.invaders.system.engine.EntityMapper;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.EventManager.EventListener;
import com.game.invaders.system.event.types.InputControlEvent;
import com.game.invaders.system.process.AbstractProcess;

public class ControllerManager extends AbstractProcess implements EventListener {
	private EntityManager manager;
	private EventManager eventManager;
	private EntityMapper managedEntities;
	
	public ControllerManager(EntityManager manager, EventManager eventManager) { 
		this.eventManager = eventManager;
		this.manager = manager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ActorComponentID.CONTROLLER, ActorComponentID.PLAYER_STATE));
	}
	@Override
	public void handle(Event e) {
		InputControlEvent ev = (InputControlEvent) e;
		int entity = managedEntities.getGroup().first();
		PlayerShipStateC state_c = (PlayerShipStateC) manager.componentFor(entity, ActorComponentID.PLAYER_STATE);
		switch(ev.getControlType()) {
			case MOVE_LEFT:
				state_c.setState(PlayerState.MOVING_LEFT);
				break;
			case MOVE_RIGHT:
				state_c.setState(PlayerState.MOVING_RIGHT);
				break;
			case SHOOT:
				state_c.setState(PlayerState.FIRING);
				break;
			case STOP_MOVE:
				state_c.setState(PlayerState.STOPPED);
				break;
		}
	}
	@Override
	public void init() {
		eventManager.registerHandler(this, EnumSet.of(Event.EventType.INPUT_CONTROL));
	}
	@Override
	public void update(float dt) {
	}
}
