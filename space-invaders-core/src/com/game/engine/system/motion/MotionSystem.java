package com.game.engine.system.motion;

import java.util.HashSet;
import java.util.Set;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.system.entity.EntityMapper;
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
		Set<Class<? extends Component>> cs = new HashSet<Class<? extends Component>>();
		cs.add(Motion.class);
		cs.add(Position.class);
		managedEntities = new EntityMapper(manager, eventManager, cs);
	}
	@Override
	public void update(float dt) {
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			Motion physics_c = (Motion) manager.componentFor(e, Motion.class);
			Position position_c = (Position) manager.componentFor(e, Position.class);
			Vector2 speed = physics_c.getSpeed();
			position_c.getPos().mulAdd(speed, dt/1000);
		}
	}
}
