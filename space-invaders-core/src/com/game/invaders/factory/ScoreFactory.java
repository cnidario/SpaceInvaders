package com.game.invaders.factory;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.component.Position;
import com.game.engine.component.TextRenderable;
import com.game.engine.node.Node;
import com.game.invaders.component.Score;

public class ScoreFactory {
	public Node create(Node rootNode) {
		Node score = rootNode.create(
				new Score(0),
				new Position(new Vector2(100, 750)),
				new TextRenderable("0")
				);
		return score;
	}
}
