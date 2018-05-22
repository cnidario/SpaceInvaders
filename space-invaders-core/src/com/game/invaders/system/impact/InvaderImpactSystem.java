package com.game.invaders.system.impact;

import com.game.engine.component.Animation;
import com.game.engine.component.Impact;
import com.game.engine.component.ShortLife;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.node.Node;
import com.game.engine.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.Invader;
import com.game.invaders.component.TiltExploding;
import com.game.invaders.component.Invader.InvaderStateID;
import com.game.invaders.component.InvaderImpact;
import com.game.invaders.data.GameConfigData;

public class InvaderImpactSystem extends AbstractProcess {
	private NodeSet nodeSet;
	
	@SuppressWarnings("unchecked")
	public InvaderImpactSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		nodeSet = entityNodeSetFactory.create(Impact.class);
	}
	@Override
	public void init() {
	}
	@Override
	public void update(float dt) {
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
			node.create(new InvaderImpact(invader.getId(), 0), new ShortLife());
		}
	}
}
