package com.game.invaders.system.invader;

import java.util.EnumSet;

import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.Component.ComponentID;
import com.game.engine.entity.component.GroupParent;
import com.game.engine.entity.component.Position;
import com.game.engine.system.EntityMapper;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.process.AbstractProcess;

/**
 * Controla el movimiento individual respecto al grupo Es decir, actualiza la
 * posición en función de su posición dentro del grupo y la posición de la caja
 * del grupo Simplemente un movimiento relativo
 */
public class InvaderGroupMovementSystem extends AbstractProcess {
	private EventSystem eventManager;
	private EntityMapper managedEntities;
	private EntityManager manager;

	public InvaderGroupMovementSystem(EntityManager manager, EventSystem eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		managedEntities = new EntityMapper(manager, eventManager,
				EnumSet.of(ComponentID.GROUP_PARENT, ComponentID.POSITION));
	}

	@Override
	public void update(float dt) {
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext;) {
			int e = iter.next();
			// FIXME problema en esta estructura de árbol que no actualiza en orden de árbol
			GroupParent child_c = (GroupParent) manager.componentFor(e, ComponentID.GROUP_PARENT);
			Position pos_c = (Position) manager.componentFor(e, ComponentID.POSITION);
			int parent = child_c.getParent();
			Position group_pos_c = (Position) manager.componentFor(parent, ComponentID.POSITION);
			if (group_pos_c != null) {
				pos_c.getPos().set(child_c.getOffset().cpy().add(group_pos_c.getPos()));
			}
		}
	}
}
