package com.game.engine.entity.observer;

public interface EntitySubscriber {
	void attach(EntityObserver o);
	void detach(EntityObserver o);
}
