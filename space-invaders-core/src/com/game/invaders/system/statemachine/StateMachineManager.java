package com.game.invaders.system.statemachine;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.game.invaders.scene.actor.Actor;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.components.StateMachineActorC;
import com.game.invaders.statemachine.State;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.Event.EventType;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.EventManager.EventListener;
import com.game.invaders.system.event.types.ComponentAddedEvent;
import com.game.invaders.system.event.types.ComponentRemovedEvent;
import com.game.invaders.system.process.AbstractProcess;

public class StateMachineManager extends AbstractProcess implements EventListener {
	private EventManager event_manager;
	List<Actor> actors = new ArrayList<Actor>();

	public StateMachineManager(EventManager event_manager) {
		super();
		this.event_manager = event_manager;
	}
	@Override
	public void init() {
		event_manager.registerHandler(this, EnumSet.of(EventType.COMPONENT_ADDED, EventType.COMPONENT_REMOVED));
	}
	@Override
	public void update(float dt) {
		for (Actor actor : actors) {
			@SuppressWarnings("unchecked")
			StateMachineActorC<State> stcomp = (StateMachineActorC<State>) actor.getComponent(ActorComponentID.STATE_MACHINE);
			stcomp.getStateMachine().update(dt, actor);
		}
	}
	@Override
	public void handle(Event e) {
		switch(e.getType()) {
			case COMPONENT_ADDED:
				ComponentAddedEvent ev = (ComponentAddedEvent) e;
				Actor actor = (Actor)ev.getComponent().parent();
				if(ev.getComponent().getID() == ActorComponentID.STATE_MACHINE && !actors.contains(actor))
					actors.add(actor);
				break;
			case COMPONENT_REMOVED:
				ComponentRemovedEvent eev = (ComponentRemovedEvent) e;
				if(eev.getComponent().getID() == ActorComponentID.STATE_MACHINE)
					actors.remove((Actor)eev.getComponent().parent());
				break;
			default: break;
		}
	}
}
