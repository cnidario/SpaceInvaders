package com.game.engine.system.entity.node;

import java.util.List;
import com.game.engine.entity.Component;
import com.game.engine.system.entity.EntityDAO;
import com.game.engine.system.entity.lifecycle.command.DeleteEntityCommand;
import com.game.engine.system.entity.lifecycle.command.EntityLifecycleCommandProcessor;

public class EntityNodeMediator {
	private EntityLifecycleCommandProcessor entityLifecycleCommandProcessor;
	private EntityDAO entityDao;
	
	public EntityNodeMediator(EntityLifecycleCommandProcessor entityLifecycleCommandProcessor, EntityDAO entityDao) {
		super();
		this.entityLifecycleCommandProcessor = entityLifecycleCommandProcessor;
		this.entityDao = entityDao;
	}
	public int createEntity(List<Component> components) {
		//entityLifecycleCommandProcessor.queueCommand();
		return 0;
	}
	public Component component(int entity, Class<? extends Component> component) {
		return entityDao.componentFor(entity, component);
	}
	public void updateEntity(int entity, Component component) {
		entityDao.updateComponent(entity, component);
	}
	public void deleteEntity(int entity) {
		entityLifecycleCommandProcessor.queueCommand(new DeleteEntityCommand(entity));
	}
	public void deleteComponent(int entity, Class<? extends Component> component) {
		//entityLifecycleCommandProcessor.queueCommand(new DeleteEntityCommand(entity));
	}
	public void addComponent(int entity, Component component) {
		entityDao.addComponent(entity, component);
	}
}
