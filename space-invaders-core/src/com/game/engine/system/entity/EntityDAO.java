package com.game.engine.system.entity;

import java.util.Set;
import com.game.engine.entity.Component;

public interface EntityDAO {
	int createEntity();
	Set<Integer> allEntities();
	boolean hasEntity(int entity);
	Set<Component> componentsFor(int entity);
	Component componentFor(int entity, Class<? extends Component> component);
	void addComponent(int entity, Component component);
	void updateComponent(int entity, Component component);
	void removeComponent(int entity, Class<? extends Component> component);
	void removeEntity(int entity);
}
