package com.game.invaders.scene.actor;


public interface ActorComponent  {
	public enum ActorComponentID {
		POSITION,
		RENDERABLE,
		COLLISION,
		CONTROLLER,
		PHYSICS,
		INVADER_STATE
	}
	ActorComponentID getID();
}
