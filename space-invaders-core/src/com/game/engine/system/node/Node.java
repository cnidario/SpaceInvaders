package com.game.engine.system.node;

import java.util.List;

import com.game.engine.entity.Component;

public interface Node extends Comparable<Node> {
	int getId();
	void delete();
	Component component(Class<? extends Component> component);
	void add(Component component);
	void add(Component... components);
	void update(Component component);
	void deleteComponent(Class<? extends Component> component);
	Node create(List<Component> components);
	Node create(Component... components);
	Node asNode(int entity);
}
