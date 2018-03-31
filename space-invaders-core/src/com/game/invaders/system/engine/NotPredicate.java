package com.game.invaders.system.engine;

import java.util.Set;

import com.game.invaders.scene.actor.ActorComponent;

public class NotPredicate implements ComponentPredicate {
	private ComponentPredicate a;

	public NotPredicate(ComponentPredicate a) {
		super();
		this.a = a;
	}
	public ComponentPredicate getA() {
		return a;
	}
	public void setA(ComponentPredicate a) {
		this.a = a;
	}
	@Override
	public boolean match(Set<ActorComponent> components) {
		return !a.match(components);
	}
}
