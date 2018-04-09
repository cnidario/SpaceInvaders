package com.game.engine.system.controller;

import java.util.EnumSet;

import com.game.engine.entity.EntityManager;
import com.game.engine.entity.Component.ComponentID;
import com.game.engine.system.EntityMapper;
import com.game.engine.system.event.Event;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.EventSystem.EventListener;
import com.game.engine.system.event.types.InputControlEvent;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.component.PlayerShip.PlayerState;

public class ControllerSystem extends AbstractProcess implements EventListener {
	private EntityManager manager;
	private EventSystem eventManager;
	private EntityMapper managedEntities;
	
	public ControllerSystem(EntityManager manager, EventSystem eventManager) { 
		this.eventManager = eventManager;
		this.manager = manager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ComponentID.USER_CONTROLLED, ComponentID.PLAYER_SHIP));
	}
	@Override
	public void handle(Event e) {
		InputControlEvent ev = (InputControlEvent) e;
		int entity = managedEntities.getGroup().first();
		PlayerShip state_c = (PlayerShip) manager.componentFor(entity, ComponentID.PLAYER_SHIP);
		
		switch(ev.getControlType()) {
			case MOVE_LEFT:
				state_c.setState(PlayerState.MOVING_LEFT);
				break;
			case MOVE_RIGHT:
				state_c.setState(PlayerState.MOVING_RIGHT);
				break;
			case SHOOT:
				state_c.setFiring(true);
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
