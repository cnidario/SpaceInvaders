package com.game.invaders.factory;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.component.Collision;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.Renderable;
import com.game.engine.entity.component.ShortLife;
import com.game.engine.system.node.Node;
import com.game.invaders.component.ShootEmitted;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;
import com.game.invaders.system.impact.CollisionGroup;

public class ShootFactory {
	public ShootFactory() {
	}
	public Node create(Node ship) {
		Position pos_c = (Position) ship.component(Position.class);
		Vector2 shootp = pos_c.getPos().cpy();
		shootp.x += GameResources.PLAYER.IMAGE.getWidth() / 2 - GameResources.PLAYER.SHOOT_IMG.getWidth() / 2;
		Node shoot = ship.create(
				new Position(shootp),
				new Motion(new Vector2(0, GameConfigData.PLAYER.SHOOT_SPEED)),
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
