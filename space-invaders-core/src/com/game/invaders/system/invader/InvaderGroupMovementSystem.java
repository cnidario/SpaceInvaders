package com.game.invaders.system.invader;

import com.game.engine.entity.component.GroupParent;
import com.game.engine.entity.component.Position;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;

/**
 * Controla el movimiento individual respecto al grupo Es decir, actualiza la
 * posición en función de su posición dentro del grupo y la posición de la caja
 * del grupo Simplemente un movimiento relativo
 */
public class InvaderGroupMovementSystem extends AbstractProcess {
	private NodeSet nodeSet;

	@SuppressWarnings("unchecked")
	public InvaderGroupMovementSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		nodeSet = entityNodeSetFactory.create(GroupParent.class, Position.class);
	}

	@Override
	public void update(float dt) {
		for (Node node : nodeSet) {
			GroupParent child_c = (GroupParent) node.component(GroupParent.class);
			Position pos_c = (Position) node.component(Position.class);
			Node parent = node.asNode(child_c.getParent());
			Position group_pos_c = (Position) parent.component(Position.class);
			if (group_pos_c != null) { //XXX actualizar con posibilidad de aviso por evento? (updateComp)
				pos_c.getPos().set(child_c.getOffset().cpy().add(group_pos_c.getPos()));
			}
		}
	}
}
