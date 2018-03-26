package com.game.invaders.scene.actor.components;

import java.util.ArrayList;
import java.util.List;

import com.game.invaders.scene.SceneNode;
import com.game.invaders.scene.actor.Actor;
import com.game.invaders.scene.actor.ActorComponent;

public abstract class AbstractActorComponent implements ActorComponent {
	private Actor parent;
	private final ActorComponentID type;
	public AbstractActorComponent(Actor parent, ActorComponentID type) {
		this.parent = parent;
		this.type = type;
	}
	@Override
	public SceneNode parent() {
		return parent;
	}
	@Override
	public void setParent(SceneNode parent) {
		this.parent = (Actor)parent;
	}
	@Override
	public ActorComponentID getID() { return type; }
	@Override
	public boolean isRoot() { return false; }
	@Override
	public List<SceneNode> children() { return new ArrayList<SceneNode>(); }
}
