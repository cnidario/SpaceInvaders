package com.game.engine.node;

import java.util.Set;
import com.game.engine.component.Component;

public interface NodeSet extends Iterable<Node> {
	boolean contains(int entity);
	boolean isEmpty();
	boolean changed();
	boolean acceptsEntity(Set<Component> components);
	Set<Class<? extends Component>> requiredComponents();
	void add(int entity);
	void remove(int entity);
	void update(int entity);
	int count();
	Node one();
}
