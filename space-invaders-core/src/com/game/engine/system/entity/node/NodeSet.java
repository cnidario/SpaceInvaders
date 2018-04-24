package com.game.engine.system.entity.node;

public interface NodeSet extends Iterable<Node> {
	boolean has(int entity);
	boolean isEmpty();
	boolean changed();
}
