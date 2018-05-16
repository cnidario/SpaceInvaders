package com.game.invaders.factory;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.component.Animation;
import com.game.engine.component.Collision;
import com.game.engine.component.GroupParent;
import com.game.engine.component.Position;
import com.game.engine.component.Renderable;
import com.game.engine.system.node.Node;
import com.game.invaders.component.Invader;
import com.game.invaders.component.Invader.InvaderStateID;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;
import com.game.invaders.system.impact.CollisionGroup;

public class InvaderFactory {
	public InvaderFactory() {
	}
	private Node baseInvader(Node group) {
		Node base = group.create(
				new Renderable(GameResources.INVADER.INVADERS[0][0]),
				new Collision<CollisionGroup>(
						GameResources.INVADER.BBOX, 
						EnumSet.noneOf(CollisionGroup.class), //collides with none
						EnumSet.of(CollisionGroup.INVADER)),
				new Position(new Vector2()),
				new Invader(InvaderStateID.ALIVE)
				);
		return base;
	}
	public Node create(Node invaderGroup, Vector2 pos, int type) {
		Node invader = baseInvader(invaderGroup);
		invader.add(
				new Animation(
						GameResources.INVADER.INVADERS[type],
						GameConfigData.INVADER.ANIM_TIME,
						true),
				new GroupParent(pos, invaderGroup.getId())
				);
		return invader;
	}
}
