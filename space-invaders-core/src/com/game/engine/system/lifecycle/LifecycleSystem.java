package com.game.engine.system.lifecycle;

import java.util.ArrayList;
import java.util.Collection;

import com.game.engine.entity.Component;
import com.game.engine.entity.EntityDAO;
import com.game.engine.entity.observer.EntityObserver;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.node.component.DeleteComponent;
import com.game.engine.system.node.component.DeleteEntity;
import com.game.engine.system.process.AbstractProcess;

public class LifecycleSystem extends AbstractProcess {
	private NodeSet deletedEntities;
	private NodeSet deletedComponents;
	private EntityDAO entityDao;
	private EntityObserver observer;
	
	@SuppressWarnings("unchecked")
	public LifecycleSystem(EntityNodeSetFactory entityNodeSetFactory, EntityDAO entityDao, EntityObserver observer) {
		deletedEntities = entityNodeSetFactory.create(DeleteEntity.class);
		deletedComponents = entityNodeSetFactory.create(DeleteComponent.class);
		this.entityDao = entityDao;
		this.observer = observer;
	}
	private Collection<Node> toList(Iterable<Node> c) {
		Collection<Node> res = new ArrayList<Node>();
		for (Node node : c) {
			res.add(node);
		}
		return res;
	}
	@Override
	public void update(float dt) {
		for (Node node : toList(deletedEntities)) {
			int entity = node.getId();
			entityDao.deleteEntity(entity);
			observer.entityDeleted(entity);
		}
		for (Node node : toList(deletedComponents)) {
			int entity = node.getId();
			DeleteComponent del_c = (DeleteComponent) node.component(DeleteComponent.class);
			Class<? extends Component> clazz = del_c.getClazz();
			entityDao.deleteComponent(entity, clazz);
			observer.componentDeleted(entity, clazz);
		}
	}
}
