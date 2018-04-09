package com.game.engine.system.physics;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.Component.ComponentID;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.system.EntityMapper;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.process.AbstractProcess;

public class MotionSystem extends AbstractProcess {
	private EventSystem eventManager;
	private EntityMapper managedEntities;
	private EntityManager manager;
	
	public MotionSystem(EntityManager manager, EventSystem eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ComponentID.MOTION, ComponentID.POSITION));
	}
	@Override
	public void update(float dt) {
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			Motion physics_c = (Motion) manager.componentFor(e, ComponentID.MOTION);
			Position position_c = (Position) manager.componentFor(e, ComponentID.POSITION);
			Vector2 speed = physics_c.getSpeed();
			position_c.getPos().mulAdd(speed, dt/1000);
		}
	}
}
