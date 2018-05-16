package com.game.engine.system.node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.badlogic.gdx.utils.IntSet;
import com.game.engine.component.Component;
import com.game.engine.entity.observer.EntityObserver;
/**
 * Un NodeSet contiene un conjunto de entidades dinámico
 * Un NodeSet tiene una lista de tipos de componente que le interesan
 * Reglas de notificaciones:
 * 	- entidad borrada -> sólo si el nodeset la contenía
 *  - componente añadido -> sólo si le interesa el componente
 *  - componente borrado -> sólo si le interesa y contiene la entidad
 *  - componente actualizado -> sólo si le interesa y contiene la entidad
 */
public class NodeSetManager implements EntityObserver {
	private Map<Class<? extends Component>, Set<NodeSet>> compMappings;
	private Map<NodeSet, IntSet> managedEntities;
	
	public NodeSetManager() {
		super();
		compMappings = new HashMap<Class<? extends Component>, Set<NodeSet>>();
		managedEntities = new HashMap<NodeSet, IntSet>();
	}
	private Set<NodeSet> nodeSetForComponent(Class<? extends Component> clazz) {
		Set<NodeSet> set = compMappings.get(clazz);
		if(set == null)
			return new HashSet<NodeSet>();
		return set;
	}
	private void postcheckNodeSetEntity(NodeSet nodeSet, int entity) {
		if(nodeSet.contains(entity)) {
			managedEntities.get(nodeSet).add(entity);
		} else {
			managedEntities.get(nodeSet).remove(entity);
		}
	}
	public void registerNodeSet(NodeSet nodeSet, Set<Class<? extends Component>> comps) {
		for (Class<? extends Component> clazz : comps) {
			Set<NodeSet> l = compMappings.get(clazz);
			if(l == null) {
				l = new HashSet<NodeSet>();
				compMappings.put(clazz, l);
			}
			l.add(nodeSet);
		}
		managedEntities.put(nodeSet, new IntSet());
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
		for (NodeSet nodeSet : nodeSetForComponent(component.getClass())) {
			nodeSet.componentAdded(entity, components, component);
			postcheckNodeSetEntity(nodeSet, entity);
		}
	}
	@Override
	public void componentUpdated(int entity, Component component) {
		for (NodeSet nodeSet : nodeSetForComponent(component.getClass())) {
			if(managedEntities.get(nodeSet).contains(entity)) {
				nodeSet.componentUpdated(entity, component);
				postcheckNodeSetEntity(nodeSet, entity);
			}
		}
	}
	@Override
	public void componentDeleted(int entity, Class<? extends Component> component) {
		for (NodeSet nodeSet : nodeSetForComponent(component)) {
			if(managedEntities.get(nodeSet).contains(entity)) {
				nodeSet.componentDeleted(entity, component);
				postcheckNodeSetEntity(nodeSet, entity);
			}
		}
	}
}
