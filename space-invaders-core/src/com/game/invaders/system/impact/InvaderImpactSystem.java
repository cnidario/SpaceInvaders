package com.game.invaders.system.impact;

import com.game.engine.entity.component.Animation;
import com.game.engine.entity.component.Impact;
import com.game.engine.system.entity.node.EntityNodeSetFactory;
import com.game.engine.system.entity.node.Node;
import com.game.engine.system.entity.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.Invader;
import com.game.invaders.component.TiltExploding;
import com.game.invaders.component.Invader.InvaderStateID;
import com.game.invaders.data.GameConfigData;

public class InvaderImpactSystem extends AbstractProcess {
	private NodeSet nodeSet;
	
	@SuppressWarnings("unchecked")
	public InvaderImpactSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		nodeSet = entityNodeSetFactory.create(Impact.class);
	}
	private void handle() {
		for (Node node : nodeSet) {
			Impact impact_c = (Impact) node.component(Impact.class);
			Node shoot = node.asNode(impact_c.getE1());
			Node invader = node.asNode(impact_c.getE2());
			Invader invader_c = (Invader) invader.component(Invader.class);
			invader_c.setStateID(InvaderStateID.DYING);
			invader_c.setDyingTime(GameConfigData.INVADER.EXPLOSION_DELAY);
			TiltExploding explo_c = new TiltExploding(6, GameConfigData.INVADER.EXPLOSION_DELAY/5);
			invader.add(explo_c);
			invader.deleteComponent(Animation.class);
			shoot.delete();
		}
	}
	@Override
	public void init() {
	}
	@Override
	public void update(float dt) {
		handle();
	}
}
