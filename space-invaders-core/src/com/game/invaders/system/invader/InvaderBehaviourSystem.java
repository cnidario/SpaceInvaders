package com.game.invaders.system.invader;

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
import com.game.invaders.data.GameConfigData;

/**
 * Controla el movimiento en grupo de la caja de invaders
 */
public class InvaderBehaviourSystem extends AbstractProcess {
	private EntityManager manager;
	private EntityMapper managedEntities;
	private EventSystem eventManager;

	public InvaderBehaviourSystem(EntityManager manager, EventSystem eventManager) {
		super();
		this.manager = manager;
		this.eventManager = eventManager;
		managedEntities = new EntityMapper(manager, eventManager,
				EnumSet.of(ComponentID.GROUP, ComponentID.MOTION, ComponentID.POSITION));
	}

	@Override
	public void update(float dt) {
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext;) {
			int e = iter.next();
			Motion physics_comp = (Motion) manager.componentFor(e, ComponentID.MOTION);
			Position position_comp = (Position) manager.componentFor(e, ComponentID.POSITION);
			Vector2 speed = physics_comp.getSpeed();
			Vector2 pos = position_comp.getPos();
			if (pos.x > GameConfigData.INVADER.MAXX) {
				speed.x = -speed.x;
				pos.x = pos.x - (pos.x - GameConfigData.INVADER.MAXX);
			} else if (pos.x < GameConfigData.INVADER.MINX) {
				if (speed.x == GameConfigData.INVADER.MAXSPEED || speed.x == -GameConfigData.INVADER.MAXSPEED)
					speed.x = -speed.x;
				else
					speed.x = -speed.x + GameConfigData.INVADER.SPEEDINCREMENT;
				pos.x = pos.x + (GameConfigData.INVADER.MINX - pos.x);
				pos.y -= GameConfigData.INVADER.DESCEND_DISTANCE;
			}
		}
	}
}