package com.game.invaders.factory;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.Renderable;
import com.game.engine.entity.component.UserControlled;
import com.game.engine.system.node.Node;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.component.PlayerShip.PlayerState;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;

public class PlayerShipFactory {
	public PlayerShipFactory() {
	}
	public Node create(Node space) {
		Node ship = space.create(
				new Position(new Vector2()),
				new Motion(new Vector2()),
				new UserControlled(),
				new PlayerShip(PlayerState.STOPPED, GameConfigData.PLAYER.SHOOT_DELAY),
				new Renderable(GameResources.PLAYER.PLAYER)
				);
		return ship;
	}
}
