package com.game.invaders.system.controller;

import java.util.EnumSet;
import com.game.invaders.scene.actor.Actor;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.components.StateMachineActorC;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.EventManager.EventListener;
import com.game.invaders.system.event.types.InputControlEvent;
import com.game.invaders.system.process.AbstractProcess;

public class ControllerManager extends AbstractProcess implements EventListener {
	private EventManager event_manager;
	private Actor controlled;
	public ControllerManager(EventManager event_manager, Actor controlled) { //Actor controlled = sucio FIXME
		this.event_manager = event_manager;
		this.controlled = controlled;
	}
	@Override
	public void handle(Event e) {
		InputControlEvent ev = (InputControlEvent) e;
		@SuppressWarnings("unchecked")
		StateMachineActorC<PlayerState> st = (StateMachineActorC<PlayerState>) controlled.getComponent(ActorComponentID.STATE_MACHINE);
		PlayerStateMachine stm = (PlayerStateMachine) st.getStateMachine();
		switch(ev.getControlType()) {
			case MOVE_LEFT:
				
		}
	}
	@Override
	public void init() {
		event_manager.registerHandler(this, EnumSet.of(Event.EventType.INPUT_CONTROL));
	}
	@Override
	public void update(float dt) {
	}
}
