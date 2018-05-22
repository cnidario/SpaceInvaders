package com.game.engine.node.impl;

import java.util.Iterator;
import java.util.Set;
import com.badlogic.gdx.utils.IntSet;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.component.Component;
import com.game.engine.factory.EntityNodeFactory;
import com.game.engine.node.Node;
import com.game.engine.node.NodeSet;

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
	public int count() {
		return set.size;
	}
	@Override
	public Node one() {
		return iterator().next();
	}
	@Override
	public boolean acceptsEntity(Set<Component> components) {
		return true;
	}
	@Override
	public void add(int entity) {
		set.add(entity);
		changed = true;
	}
	@Override
	public void remove(int entity) {
		set.remove(entity);
		changed = true;
	}
	@Override
	public void update(int entity) {
		changed = true;
	}
	@Override
	public Set<Class<? extends Component>> requiredComponents() {
		return interestedComponents;
	}
}
