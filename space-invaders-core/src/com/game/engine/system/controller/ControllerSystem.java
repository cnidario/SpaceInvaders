package com.game.engine.system.controller;

import java.util.HashSet;
import java.util.Set;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.component.UserControlled;
import com.game.engine.system.entity.EntityMapper;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.EventSystem.EventListener;
import com.game.engine.system.event.types.InputControlEvent;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.component.PlayerShip.PlayerState;

public class ControllerSystem extends AbstractProcess implements EventListener<InputControlEvent> {
	private EntityManager manager;
	private EventSystem eventManager;
	private EntityMapper managedEntities;
	
	public ControllerSystem(EntityManager manager, EventSystem eventManager) { 
		this.eventManager = eventManager;
		this.manager = manager;
		Set<Class<? extends Component>> cs = new HashSet<Class<? extends Component>>();
		cs.add(UserControlled.class);
		cs.add(PlayerShip.class);
		managedEntities = new EntityMapper(manager, eventManager, cs);
	}
	@Override
	public void handle(InputControlEvent ev) {
		int entity = managedEntities.getGroup().first();
		PlayerShip state_c = (PlayerShip) manager.componentFor(entity, PlayerShip.class);
		
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
		eventManager.registerHandler(this, InputControlEvent.class);
	}
	@Override
	public void update(float dt) {
	}
}
