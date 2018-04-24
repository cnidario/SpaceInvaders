package com.game.engine.system.entity.lifecycle;

import java.util.ArrayList;
import java.util.List;
import com.game.engine.system.entity.EntityDAO;
import com.game.engine.system.entity.EntityNotifier;
import com.game.engine.system.entity.lifecycle.command.EntityLifecycleCommand;
import com.game.engine.system.entity.lifecycle.command.EntityLifecycleCommandProcessor;
import com.game.engine.system.process.AbstractProcess;

public class EntityLifecycleSystem extends AbstractProcess implements EntityLifecycleCommandProcessor {
	private List<EntityLifecycleCommand> commands;
	private EntityDAO entityDao;
	private EntityNotifier notifier;
	
	public EntityLifecycleSystem(EntityDAO entityDao, EntityNotifier notifier) {
		super();
		this.entityDao = entityDao;
		this.notifier = notifier;
		this.commands = new ArrayList<EntityLifecycleCommand>();
	}
	@Override
	public void update(float dt) {
		for (EntityLifecycleCommand entityLifecycleCommand : commands) {
			entityLifecycleCommand.execute(entityDao);
		}
		commands.clear();
	}
	@Override
	public void queueCommand(EntityLifecycleCommand command) {
		commands.add(command);
	}
}
