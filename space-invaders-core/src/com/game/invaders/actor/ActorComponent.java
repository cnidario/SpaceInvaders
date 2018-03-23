package com.game.invaders.actor;

public interface ActorComponent {
	public enum ActorComponentID {
		RENDERABLE,
		COLLISION,
		STATE_MACHINE,
		CONTROLLER
	}
	ActorComponentID getID();
}
