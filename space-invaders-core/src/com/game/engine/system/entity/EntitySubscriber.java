package com.game.engine.system.entity;

public interface EntitySubscriber {
	void attach(EntityObserver o);
	void detach(EntityObserver o);
}
