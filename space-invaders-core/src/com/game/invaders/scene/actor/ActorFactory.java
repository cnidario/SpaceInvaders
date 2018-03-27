package com.game.invaders.scene.actor;

import com.game.invaders.scene.NodeGroup;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.types.ComponentAddedEvent;

/**
 * Crear un actor es complicado:
 *   - Construír el objeto
 *   - Añadirlo a un árbol de escena, a un grupo, referirle al padre
 *   - Emitir evento de creación
 * Añadir componentes a este también presenta problemas:
 *   - Emitir eventos
 *   - Añadirlo al árbol de escena
 */
public class ActorFactory {
	private EventManager event_manager;
	public ActorFactory(EventManager event_manager) {
		super();
		this.event_manager = event_manager;
	}
	
	public class ActorBuilder {
		private Actor actor = new Actor();
		
		public Actor in(NodeGroup parent) { actor.setParent(parent); return actor; }
		public Actor addComponent(ActorComponent component) {
			actor.addComponent(component);
			component.setParent(actor);
			event_manager.queueEvent(new ComponentAddedEvent(component));
			return actor;
		}
	}
}
