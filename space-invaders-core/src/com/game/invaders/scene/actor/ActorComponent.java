package com.game.invaders.scene.actor;

import com.game.invaders.scene.SceneNode;

public interface ActorComponent extends SceneNode {
	public enum ActorComponentID {
		RENDERABLE,
		COLLISION,
		CONTROLLER,
		PHYSICS,
		INVADER_STATE
	}
	ActorComponentID getID();
}
