package com.game.engine.system.entity.lifecycle.command;

import com.game.engine.system.entity.EntityDAO;

public interface EntityLifecycleCommand {
	void execute(EntityDAO entityDao);
}
