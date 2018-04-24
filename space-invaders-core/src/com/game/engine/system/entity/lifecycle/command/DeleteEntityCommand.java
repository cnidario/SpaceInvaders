package com.game.engine.system.entity.lifecycle.command;

import com.game.engine.system.entity.EntityDAO;

public class DeleteEntityCommand implements EntityLifecycleCommand {
	private int entity;
	
	public DeleteEntityCommand(int entity) {
		super();
		this.entity = entity;
	}
	@Override
	public void execute(EntityDAO entityDao) {
		entityDao.removeEntity(entity);
	}
}
