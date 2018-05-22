package com.game.engine.node.impl;

import java.util.Arrays;
import java.util.List;

import com.game.engine.component.Component;
import com.game.engine.component.node.DeleteComponent;
import com.game.engine.component.node.DeleteEntity;
import com.game.engine.entity.EntityDAO;
import com.game.engine.factory.EntityNodeFactory;
import com.game.engine.node.Node;

public class EntityNode implements Node {
	private EntityDAO entityDao;
	private EntityNodeFactory entityNodeFactory;
	private int entity;
	
	public EntityNode(EntityDAO entityDao, EntityNodeFactory entityNodeFactory, int entity) {
		super();
		this.entityDao = entityDao;
		this.entityNodeFactory = entityNodeFactory;
		this.entity = entity;
	}
	public int getEntity() {
		return entity;
	}
	@Override
	public void delete() {
		add(new DeleteEntity());
	}
	@Override
	public Component component(Class<? extends Component> component) {
		return entityDao.componentFor(entity, component);
	}
	@Override
	public void update(Component component) {
		entityDao.updateComponent(entity, component);
	}
	@Override
	public void deleteComponent(Class<? extends Component> component) {
		add(new DeleteComponent(component));
	}
	@Override
	public int compareTo(Node o) {
		return entity - o.getId();
	}
	@Override
	public int getId() {
		return getEntity();
	}
	@Override
	public void add(Component component) {
		entityDao.addComponent(entity, component);
	}
	@Override
	public Node create(List<Component> components) {
		int e = entityDao.createEntity();
		for (Component component : components) {
			entityDao.addComponent(e, component);
		}
		return entityNodeFactory.create(e);
	}
	@Override
	public Node create(Component... components) {
		return create(Arrays.asList(components));
	}
	@Override
	public Node asNode(int entity) {
		return entityNodeFactory.create(entity);
	}
	@Override
	public void add(Component... components) {
		for (Component component : components) {
			add(component);
		}
	}
}
