package com.game.engine.system.entity.lifecycle.command;

public interface EntityLifecycleCommandProcessor {
	void queueCommand(EntityLifecycleCommand command);
}
