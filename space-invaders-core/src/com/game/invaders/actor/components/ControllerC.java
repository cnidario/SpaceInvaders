package com.game.invaders.actor.components;

import com.game.invaders.actor.ActorComponent;

public class ControllerC implements ActorComponent {
	@Override
	public ActorComponentID getID() { return ActorComponentID.CONTROLLER; }
}
