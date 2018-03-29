package com.game.invaders.scene.actor;

import com.game.invaders.scene.SceneGraph;
import com.game.invaders.scene.SceneNodePointer;
import com.game.invaders.system.event.Event.EventType;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.types.ActorLifeCycleEvent;
import com.game.invaders.system.event.types.ComponentAddedEvent;
import com.game.invaders.system.event.types.ComponentRemovedEvent;

public class EntityManager {
	private EventManager event_manager;
	private SceneGraph scene = new SceneGraph();

	public EntityManager(EventManager event_manager) {
		super();
		this.event_manager = event_manager;
	}
	public SceneNodePointer addEntity(SceneNodePointer parent, Actor actor) {
		SceneNodePointer place;
		place = parent.addChild(actor);
		event_manager.queueEvent(new ActorLifeCycleEvent(EventType.ACTOR_CREATED, actor));
		return place;
	}
	public SceneNodePointer addEntity(Actor actor) {
		return addEntity(scene.getRoot(), actor);
	}
	public void removeEntity(Actor actor) {
		SceneNodePointer pointer = scene.getRoot().find(actor);
		if(pointer != null) {
			pointer.remove();
			event_manager.queueEvent(new ActorLifeCycleEvent(EventType.ACTOR_DELETED, actor));
		}
		//no encontrado
	}
	public SceneNodePointer addComponent(SceneNodePointer parent, ActorComponent component) {
		SceneNodePointer place = parent.addChild(component);
		event_manager.queueEvent(new ComponentAddedEvent(component));
		return place;
	}
	public void removeComponent(SceneNodePointer parent, ActorComponent component) {
		parent.find(component).remove();
		event_manager.queueEvent(new ComponentRemovedEvent(component));
	}
	public void removeComponent(ActorComponent component) {
		removeComponent(scene.getRoot(), component);
	}
}
