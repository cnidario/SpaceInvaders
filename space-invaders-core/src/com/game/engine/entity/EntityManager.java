package com.game.engine.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntSet;
import com.game.engine.system.entity.EntityDAO;
import com.game.engine.system.entity.EntityNotifier;

public class EntityManager implements EntityDAO {
	private int lastId;
	private IntSet entities;
	private IntMap<Map<Class<? extends Component>, Component>> components;
	private EntityNotifier notifier;

	public EntityManager(EntityNotifier notifier) {
		super();
		lastId = 0;
		entities = new IntSet();
		components = new IntMap<Map<Class<? extends Component>, Component>>();
		this.notifier = notifier;
	}
	@Override
	public int createEntity() {
		entities.add(++lastId);
		components.put(lastId, new HashMap<Class<? extends Component>, Component>());
		return lastId;
	}
	@Override
	public void addComponent(int entity, Component component) {
		Map<Class<? extends Component>, Component> entityComps = components.get(entity);
		entityComps.put(component.getClass(), component);
		notifier.componentAdded(entity, componentsFor(entity), component);
	}
	@Override
	public Set<Component> componentsFor(int entity) {
		return new HashSet<Component>(components.get(entity).values());
	}
	@Override
	public Component componentFor(int entity, Class<? extends Component> clazz) {
		return components.get(entity).get(clazz);
	}
	@Override
	public IntSet allEntities() {
		return entities;
	}
	@Override
	public boolean hasEntity(int entity) {
		return components.get(entity) != null;
	}
	@Override
	public void updateComponent(int entity, Component component) {
		components.get(entity).put(component.getClass(), component);
		notifier.componentUpdated(entity, component);
	}
	@Override
	public void deleteComponent(int entity, Class<? extends Component> component) {
		Map<Class<? extends Component>, Component> entityComps = components.get(entity);
		if(entityComps != null) {
			entityComps.remove(component);
			notifier.componentDeleted(entity, component);
		}
	}
	@Override
	public void deleteEntity(int entity) {
		entities.remove(entity);
		components.remove(entity);
		notifier.entityDeleted(entity);
	}
}
