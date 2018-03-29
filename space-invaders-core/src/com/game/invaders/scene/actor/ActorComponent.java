package com.game.invaders.scene.actor;


public interface ActorComponent  {
	public enum ActorComponentID {
		RENDERABLE,
		COLLISION,
		CONTROLLER,
		PHYSICS,
		INVADER_STATE
	}
	ActorComponentID getID();
}
