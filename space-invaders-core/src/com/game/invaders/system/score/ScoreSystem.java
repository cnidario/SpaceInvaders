package com.game.invaders.system.score;

import com.game.engine.component.TextRenderable;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.node.Node;
import com.game.engine.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;
import com.game.invaders.component.InvaderExplosion;
import com.game.invaders.component.Score;

public class ScoreSystem extends AbstractProcess {
	private NodeSet scoreNodeSet;
	private NodeSet invaderDestructionNodeSet;
	
	@SuppressWarnings("unchecked")
	public ScoreSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		scoreNodeSet = entityNodeSetFactory.create(Score.class, TextRenderable.class);
		invaderDestructionNodeSet = entityNodeSetFactory.create(InvaderExplosion.class);
	}
	@Override
	public void update(float dt) {
		Node scoreNode = scoreNodeSet.one();
		Score score_c = (Score) scoreNode.component(Score.class);
		TextRenderable textrenderable_c = (TextRenderable) scoreNode.component(TextRenderable.class);
		for (Node node : invaderDestructionNodeSet) {
			//InvaderExplosion explo_c = (InvaderExplosion) node.component(InvaderExplosion.class);
			int newScore = score_c.getScore() + 100;
			score_c.setScore(newScore);
		}
		textrenderable_c.setText(score_c.getScore() + "");
	}
}
