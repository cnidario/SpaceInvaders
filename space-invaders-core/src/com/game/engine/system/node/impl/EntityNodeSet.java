package com.game.engine.system.node.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.component.Component;
import com.game.engine.factory.EntityNodeFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;

public class EntityNodeSet implements NodeSet {
	private Set<Class<? extends Component>> interestedComponents;
	private IntSet set;
	private boolean changed;
	private EntityNodeFactory entityNodeFactory;
	
	public EntityNodeSet(Set<Class<? extends Component>> interestedComponents, EntityNodeFactory entityNodeFactory) {
		super();
		this.interestedComponents = interestedComponents;
		set = new IntSet();
		changed = false;
		this.entityNodeFactory = entityNodeFactory;
	}
	private boolean componentMatches(Set<Component> components) {
		Set<Class<? extends Component>> classes = new HashSet<Class<? extends Component>>();
		for (Component c : components) {
			classes.add(c.getClass());
		}
		return classes.containsAll(interestedComponents);
	}
	private void addEntity(int entity) {
		set.add(entity);
		changed = true;
	}
	private void removeEntity(int entity) {
		set.remove(entity);
		changed = true;
	}
	protected boolean acceptsEntity(int entity, Set<Component> components) {
		return true;
	}
	@Override
	public Iterator<Node> iterator() {
		final IntSetIterator iter = set.iterator();
		return new Iterator<Node>() {
			@Override
			public boolean hasNext() {
				return iter.hasNext;
			}
			@Override
			public Node next() {
				return entityNodeFactory.create(iter.next());
			}
		};
	}
	@Override
	public boolean contains(int entity) {
		return set.contains(entity);
	}
	@Override
	public boolean isEmpty() {
		return set.size == 0;
	}
	@Override
	public boolean changed() {
		return changed;
	}
	@Override
	public Set<Class<? extends Component>> getInterestedComponents() {
		return interestedComponents;
	}
	@Override
	public void entityDeleted(int entity) {
		if(set.contains(entity)) {
			removeEntity(entity);
		}
	}
	@Override
	public void componentAdded(int entity, Set<Component> components, Component component) {
		if(!set.contains(entity) && componentMatches(components) && acceptsEntity(entity, components)) {
			addEntity(entity);
		}
	}
	@Override
	public void componentUpdated(int entity, Component component) {
		//
	}
	@Override
	public void componentDeleted(int entity, Class<? extends Component> component) {
		if(set.contains(entity) && interestedComponents.contains(component)) {
			removeEntity(entity);
		}
	}
	@Override
	public int count() {
		return set.size;
	}
	@Override
	public Node one() {
		return iterator().next();
	}
}
