package com.game.engine.node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.component.Component;
import com.game.engine.entity.EntityDAO;
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
	private EntityDAO entityManager;
	private Map<Class<? extends Component>, Set<NodeSet>> interests;
	private Map<NodeSet, IntSet> possibleInterestedEntities;
	
	public NodeSetManager(EntityDAO entityManager) {
		super();
		this.entityManager = entityManager;
		interests = new HashMap<Class<? extends Component>, Set<NodeSet>>();
		possibleInterestedEntities = new HashMap<NodeSet, IntSet>();
	}
	private Set<NodeSet> nodeSetForComponent(Class<? extends Component> clazz) {
		Set<NodeSet> set = interests.get(clazz);
		if(set == null)
			return new HashSet<NodeSet>();
		return set;
	}
	private boolean isInterestedIn(NodeSet nodeSet, int entity) {
		return possibleInterestedEntities.get(nodeSet).contains(entity);
	}
	private void setInterestedIn(NodeSet nodeSet, int entity) {
		possibleInterestedEntities.get(nodeSet).add(entity);
	}
	private void unsetInterestedIn(NodeSet nodeSet, int entity) {
		possibleInterestedEntities.get(nodeSet).remove(entity);
	}
	private Set<Class<? extends Component>> entityComponents(int entity) {
		Set<Component> cs = entityManager.componentsFor(entity);
		Set<Class<? extends Component>> classes = new HashSet<Class<? extends Component>>();
		for (Component c : cs) {
			classes.add(c.getClass());
		}
		return classes;
	}
	private boolean checkIfInterestedIn(NodeSet nodeSet, int entity) {
		return entityComponents(entity).containsAll(nodeSet.requiredComponents());
	}
	private boolean requiresComponent(NodeSet nodeSet, Class<? extends Component> clazz) {
		return nodeSet.requiredComponents().contains(clazz);
	}
	private void processJustInterestedIn(NodeSet nodeSet, int entity) {
		setInterestedIn(nodeSet, entity);
		if(nodeSet.acceptsEntity(entityManager.componentsFor(entity)))
			nodeSet.add(entity);
	}
	private void processJustUninterestedIn(NodeSet nodeSet, int entity) {
		unsetInterestedIn(nodeSet, entity);
		nodeSet.remove(entity);
	}
	private void initNodeSet(NodeSet nodeSet) {
		for (IntSetIterator iter = entityManager.allEntities().iterator(); iter.hasNext;) {
			int entity = iter.next();
			if(checkIfInterestedIn(nodeSet, entity)) {
				processJustInterestedIn(nodeSet, entity);
			}
		}
	}
	public void registerNodeSet(NodeSet nodeSet) {
		for (Class<? extends Component> clazz : nodeSet.requiredComponents()) {
			Set<NodeSet> l = interests.get(clazz);
			if(l == null) {
				l = new HashSet<NodeSet>();
				interests.put(clazz, l);
			}
			l.add(nodeSet);
		}
		possibleInterestedEntities.put(nodeSet, new IntSet());
		initNodeSet(nodeSet);
	}
	@Override
	public void entityDeleted(int entity) {
		for (NodeSet nodeSet : possibleInterestedEntities.keySet()) {
			if(isInterestedIn(nodeSet, entity)) {
				processJustUninterestedIn(nodeSet, entity);
			}
		}
	}
	@Override
	public void componentAdded(int entity, Component component) {
		//Set<Component> components = entityManager.componentsFor(entity);
		for (NodeSet nodeSet : nodeSetForComponent(component.getClass())) {
			if(!isInterestedIn(nodeSet, entity) && checkIfInterestedIn(nodeSet, entity)) {
				processJustInterestedIn(nodeSet, entity);
			}
		}
	}
	@Override
	public void componentUpdated(int entity, Component component) {
		//Set<Component> components = entityManager.componentsFor(entity);
		for (NodeSet nodeSet : nodeSetForComponent(component.getClass())) {
			if(isInterestedIn(nodeSet, entity) && requiresComponent(nodeSet, component.getClass())) {
				nodeSet.update(entity);
			}
		}
	}
	@Override
	public void componentDeleted(int entity, Class<? extends Component> component) {
		for (NodeSet nodeSet : nodeSetForComponent(component)) {
			if(nodeSet.contains(entity) && !checkIfInterestedIn(nodeSet, entity)) {
				processJustUninterestedIn(nodeSet, entity);
			}
		}
	}
}
