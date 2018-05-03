package com.game.engine.system.entity.node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.utils.IntSet;
import com.game.engine.entity.Component;
import com.game.engine.system.entity.EntityObserver;

public class NodeSetManager implements EntityObserver {
	private Map<Class<? extends Component>, Set<NodeSet>> compMappings;
	private Map<NodeSet, Set<Class<? extends Component>>> inverseCompMappings;
	private Map<NodeSet, IntSet> managedEntities;
	
	public NodeSetManager() {
		super();
		compMappings = new HashMap<Class<? extends Component>, Set<NodeSet>>();
		inverseCompMappings = new HashMap<NodeSet, Set<Class<? extends Component>>>();
		managedEntities = new HashMap<NodeSet, IntSet>();
	}
	private void postcheckNodeSetEntity(NodeSet nodeSet, int entity) {
		if(nodeSet.contains(entity)) {
			managedEntities.get(nodeSet).add(entity);
		} else {
			managedEntities.get(nodeSet).remove(entity);
		}
	}
	public void registerNodeSet(NodeSet nodeSet, Set<Class<? extends Component>> comps) {
		inverseCompMappings.put(nodeSet, comps);
		for (Class<? extends Component> clazz : comps) {
			Set<NodeSet> l = compMappings.get(clazz);
			if(l == null)
				l = new HashSet<NodeSet>();
			l.add(nodeSet);
		}
		managedEntities.put(nodeSet, new IntSet());
	}
	@Override
	public void entityAdded(int entity) {
		for (NodeSet nodeSet : managedEntities.keySet()) {
			nodeSet.entityAdded(entity);
			postcheckNodeSetEntity(nodeSet, entity);
		}
	}
	@Override
	public void entityDeleted(int entity) {
		for (NodeSet nodeSet : managedEntities.keySet()) {
			if(managedEntities.get(nodeSet).contains(entity)) {
				nodeSet.entityDeleted(entity);
				postcheckNodeSetEntity(nodeSet, entity);
			}
		}
	}
	@Override
	public void componentAdded(int entity, Set<Component> components, Component component) {
		for (NodeSet nodeSet : managedEntities.keySet()) {
			if(!managedEntities.get(nodeSet).contains(entity)) {
				nodeSet.componentAdded(entity, components, component);
				postcheckNodeSetEntity(nodeSet, entity);
			}
		}
	}
	@Override
	public void componentUpdated(int entity, Component component) {
		for (NodeSet nodeSet : managedEntities.keySet()) {
			if(managedEntities.get(nodeSet).contains(entity)) {
				nodeSet.componentUpdated(entity, component);
				postcheckNodeSetEntity(nodeSet, entity);
			}
		}
	}
	@Override
	public void componentDeleted(int entity, Class<? extends Component> component) {
		for (NodeSet nodeSet : managedEntities.keySet()) {
			if(managedEntities.get(nodeSet).contains(entity)) {
				nodeSet.componentDeleted(entity, component);
				postcheckNodeSetEntity(nodeSet, entity);
			}
		}
	}
}
