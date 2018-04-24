package com.game.engine.system.entity.node;

import java.util.List;
import com.game.engine.entity.Component;

public interface EntityNodeMediator {
	int createEntity(List<Component> components);
	Component component(int entity, Class<? extends Component> component);
	void updateEntity(int entity, Component component);
	void deleteEntity(int entity);
	void deleteComponent(int entity, Class<? extends Component> component);
	void addComponent(int entity, Component component);
}
