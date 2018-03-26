package com.game.invaders.subsystem.physics;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.game.invaders.scene.actor.Actor;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.components.PhysicsActorC;
import com.game.invaders.subsystem.event.Event;
import com.game.invaders.subsystem.event.EventManager;
import com.game.invaders.subsystem.event.EventManager.EventListener;
import com.game.invaders.subsystem.event.types.ComponentAddedEvent;
import com.game.invaders.subsystem.event.types.ComponentRemovedEvent;
import com.game.invaders.subsystem.process.AbstractProcess;

public class PhysicsManager extends AbstractProcess implements EventListener {
	private EventManager event_manager;
	private List<Actor> actors = new ArrayList<Actor>();
	
	public PhysicsManager(EventManager event_manager) {
		super();
		this.event_manager = event_manager;
	}
	public void update(float dt) {
		for (Actor actor : actors) {
			PhysicsActorC c = (PhysicsActorC) actor.getComponent(ActorComponentID.PHYSICS);
			Vector2 speed = c.getSpeed();
			actor.getPos().mulAdd(speed, dt);
		}
	}
	@Override
	public void handle(Event e) {
		switch(e.getType()) {
			case COMPONENT_ADDED:
				ComponentAddedEvent ev = (ComponentAddedEvent) e;
				if(ev.getComponent().getID() == ActorComponentID.PHYSICS)
					actors.add((Actor)ev.getComponent().parent());
				break;
			case COMPONENT_REMOVED:
				ComponentRemovedEvent eev = (ComponentRemovedEvent) e;
				if(eev.getComponent().getID() == ActorComponentID.PHYSICS)
					actors.remove((Actor)eev.getComponent().parent());
				break;
		default:
			break;
		}
	}
	@Override
	public void init() {
		event_manager.registerHandler(this, EnumSet.of(Event.EventType.COMPONENT_ADDED, Event.EventType.COMPONENT_REMOVED));
	}
}
