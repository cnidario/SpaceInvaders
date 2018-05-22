package com.game.invaders.factory;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.component.Collision;
import com.game.engine.component.Motion;
import com.game.engine.component.Position;
import com.game.engine.component.Renderable;
import com.game.engine.component.ShortLife;
import com.game.engine.node.Node;
import com.game.invaders.component.ShootEmitted;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;
import com.game.invaders.system.impact.CollisionGroup;

public class ShootFactory {
	public ShootFactory() {
	}
	public Node create(Node ship, Vector2 pos, Vector2 dir) {
		Vector2 shootp = pos;
		Node shoot = ship.create(
				new Position(shootp),
				new Motion(dir.scl(GameConfigData.PLAYER.SHOOT_SPEED)),
				new Collision<CollisionGroup>(
						GameResources.PLAYER.SHOOT_BBOX,
						EnumSet.of(CollisionGroup.INVADER),
						EnumSet.of(CollisionGroup.PLAYER_SHOOT)),
				new Renderable(GameResources.PLAYER.SHOOT)
				);
		ship.create(new ShootEmitted(), new ShortLife());
		return shoot;
	}
}
