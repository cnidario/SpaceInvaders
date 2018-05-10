package com.game.engine.system.entity.node;

import java.util.Set;
import com.game.engine.entity.Component;
import com.game.engine.system.entity.EntityObserver;

public interface NodeSet extends Iterable<Node>, EntityObserver {
	boolean contains(int entity);
	boolean isEmpty();
	boolean changed();
	int count();
	Set<Class<? extends Component>> getInterestedComponents();
	Node one();
}
