package com.game.invaders.system.logic.invader;

import java.util.EnumSet;

import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.scene.actor.components.ChildOfGroupC;
import com.game.invaders.scene.actor.components.PositionActorC;
import com.game.invaders.system.engine.EntityMapper;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.process.AbstractProcess;

public class InvaderGroupMovementSystem extends AbstractProcess {
	private EventManager eventManager;
	private EntityMapper managedEntities;
	private EntityManager manager;
	
	public InvaderGroupMovementSystem(EntityManager manager, EventManager eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ActorComponentID.GROUP_CHILD, ActorComponentID.POSITION));
	}
	@Override
	public void update(float dt) {
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			//FIXME problema en esta estructura de árbol que no actualiza en orden de árbol
			ChildOfGroupC child_c = (ChildOfGroupC) manager.componentFor(e, ActorComponentID.GROUP_CHILD);
			PositionActorC pos_c = (PositionActorC) manager.componentFor(e, ActorComponentID.POSITION);
			int parent = child_c.getParent();
			PositionActorC group_pos_c = (PositionActorC) manager.componentFor(parent, ActorComponentID.POSITION);
			if(group_pos_c != null) {
				pos_c.getPos().set(child_c.getOffset().cpy().add(group_pos_c.getPos()));
			}
		}
	}
}
