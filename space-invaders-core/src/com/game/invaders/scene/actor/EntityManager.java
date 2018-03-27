package com.game.invaders.scene.actor;

import java.util.ArrayList;
import java.util.List;

import com.game.invaders.system.event.Event.EventType;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.types.ActorLifeCycleEvent;
import com.game.invaders.system.event.types.ComponentAddedEvent;
import com.game.invaders.system.event.types.ComponentRemovedEvent;

public class EntityManager {
	private EventManager event_manager;
	private List<Actor> entities = new ArrayList<Actor>();

	public EntityManager(EventManager event_manager) {
		super();
		this.event_manager = event_manager;
	}
	public void addEntity(Actor actor) {
		entities.add(actor);
		event_manager.queueEvent(new ActorLifeCycleEvent(EventType.ACTOR_CREATED, actor));
	}
	public void removeEntity(Actor actor) {
		entities.remove(actor);
		event_manager.queueEvent(new ActorLifeCycleEvent(EventType.ACTOR_DELETED, actor));
	}
	public void addComponent(Actor actor, ActorComponent component) {
		actor.children().add(component);
		event_manager.queueEvent(new ComponentAddedEvent(component));
	}
	public void removeComponent(Actor actor, ActorComponent component) {
		actor.children().remove(component);
		event_manager.queueEvent(new ComponentRemovedEvent(component));
	}
}
