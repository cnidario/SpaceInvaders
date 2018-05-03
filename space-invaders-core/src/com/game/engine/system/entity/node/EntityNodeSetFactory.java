package com.game.engine.system.entity.node;

import java.util.HashSet;
import java.util.Set;

import com.game.engine.entity.Component;

public class EntityNodeSetFactory {
	private EntityNodeFactory entityNodeFactory;
	private NodeSetManager nodeSetManager;
	
	public EntityNodeSetFactory(EntityNodeFactory entityNodeFactory, NodeSetManager nodeSetManager) {
		super();
		this.entityNodeFactory = entityNodeFactory;
		this.nodeSetManager = nodeSetManager;
	}
	public EntityNodeSet create(Class<? extends Component>... comps) {
		Set<Class<? extends Component>> cs = new HashSet<Class<? extends Component>>();
		for (Class<? extends Component> c : cs) {
			cs.add(c);
		}
		return create(cs);
	}
	public EntityNodeSet create(Set<Class<? extends Component>> interestedComponents) {
		EntityNodeSet entityNodeSet = new EntityNodeSet(interestedComponents, entityNodeFactory);
		nodeSetManager.registerNodeSet(entityNodeSet, interestedComponents);
		return entityNodeSet;
	}
}
