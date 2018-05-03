package com.game.engine.system.entity.lifecycle.command;

import com.game.engine.system.entity.EntityDAO;
import com.game.engine.system.entity.EntityObserver;

public class DeleteEntityCommand implements EntityLifecycleCommand {
	private int entity;
	
	public DeleteEntityCommand(int entity) {
		super();
		this.entity = entity;
	}
	@Override
	public void execute(EntityDAO entityDao, EntityObserver observer) {
		entityDao.deleteEntity(entity);
		observer.entityDeleted(entity);
	}
}
