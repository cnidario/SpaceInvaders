package com.game.invaders.system.invader;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.component.Group;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.data.GameConfigData;

/**
 * Controla el movimiento en grupo de la caja de invaders
 */
public class InvaderBehaviourSystem extends AbstractProcess {
	private NodeSet nodeSet;

	@SuppressWarnings("unchecked")
	public InvaderBehaviourSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		nodeSet = entityNodeSetFactory.create(Motion.class, Position.class, Group.class);
	}

	@Override
	public void update(float dt) {
		for (Node node : nodeSet) {
			Motion physics_comp = (Motion) node.component(Motion.class);
			Position position_comp = (Position) node.component(Position.class);
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
