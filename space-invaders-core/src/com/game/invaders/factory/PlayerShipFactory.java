package com.game.invaders.factory;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.component.Facing;
import com.game.engine.component.Motion;
import com.game.engine.component.Position;
import com.game.engine.component.Renderable;
import com.game.engine.component.UserControlled;
import com.game.engine.node.Node;
import com.game.invaders.component.FiringBehaviour;
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
				new PlayerShip(PlayerState.STOPPED),
				new Renderable(GameResources.PLAYER.PLAYER),
				new FiringBehaviour(false, GameConfigData.PLAYER.SHOOT_DELAY),
				new Facing(new Vector2(0, 1), new Vector2(50, 64)) 
				);
		return ship;
	}
}
