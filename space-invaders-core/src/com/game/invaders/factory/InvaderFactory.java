package com.game.invaders.factory;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.component.Collision;
import com.game.engine.component.GroupParent;
import com.game.engine.component.Position;
import com.game.engine.component.Renderable;
import com.game.engine.factory.AnimationFactory;
import com.game.engine.node.Node;
import com.game.invaders.component.Invader;
import com.game.invaders.component.Invader.InvaderStateID;
import com.game.invaders.data.GameResources;
import com.game.invaders.system.impact.CollisionGroup;

public class InvaderFactory {
	private AnimationFactory animationFactory;
	
	public InvaderFactory(AnimationFactory animationFactory) {
		this.animationFactory= animationFactory;
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
	public Node create(Node invaderGroup, Node animation, Vector2 pos, int type) {
		Node invader = baseInvader(invaderGroup);
		invader.add(
				animationFactory.newInstance(animation),
				new GroupParent(pos, invaderGroup.getId())
				);
		return invader;
	}
}
