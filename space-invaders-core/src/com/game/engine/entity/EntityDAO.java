package com.game.engine.entity;

import java.util.Set;
import com.badlogic.gdx.utils.IntSet;
import com.game.engine.component.Component;

public interface EntityDAO {
	int createEntity();
	IntSet allEntities();
	boolean hasEntity(int entity);
	Set<Component> componentsFor(int entity);
	Component componentFor(int entity, Class<? extends Component> component);
	void addComponent(int entity, Component component);
	void updateComponent(int entity, Component component);
	void deleteComponent(int entity, Class<? extends Component> component);
	void deleteEntity(int entity);
}
