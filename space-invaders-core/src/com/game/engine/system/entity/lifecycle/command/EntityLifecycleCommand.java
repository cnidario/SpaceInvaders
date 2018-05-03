package com.game.engine.system.entity.lifecycle.command;

import com.game.engine.system.entity.EntityDAO;
import com.game.engine.system.entity.EntityObserver;

public interface EntityLifecycleCommand {
	void execute(EntityDAO entityDao, EntityObserver observer);
}
