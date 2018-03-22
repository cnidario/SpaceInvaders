package com.game.invaders.actor;

public interface ActorComponent {
	public enum ActorComponentID {
		RENDERABLE,
		COLLISION,
		ANIMATION_RENDERABLE,
		STATE_MACHINE
	}
	ActorComponentID getID();
}
