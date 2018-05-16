package com.game.invaders.factory;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.component.Group;
import com.game.engine.component.Motion;
import com.game.engine.component.Position;
import com.game.engine.system.node.Node;
import com.game.invaders.data.GameConfigData;

public class InvaderGroupFactory {
	public InvaderGroupFactory() {
	}
	public Node create(Node space) {
		Node group = space.create(
				new Group(),
				new Position(new Vector2(GameConfigData.INVADER.MINX, 800)),
				new Motion(new Vector2(GameConfigData.INVADER.MINSPEED, 0))
				);
		return group;
	}
}
