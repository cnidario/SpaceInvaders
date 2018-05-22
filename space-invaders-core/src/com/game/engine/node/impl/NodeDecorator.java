package com.game.engine.node.impl;

import java.util.List;

import com.game.engine.component.Component;
import com.game.engine.node.Node;

public abstract class NodeDecorator implements Node {
	protected Node delegate;
	
	public NodeDecorator(Node delegate) {
		super();
		this.delegate = delegate;
	}
	@Override
	public int compareTo(Node o) {
		return delegate.compareTo(o);
	}
	@Override
	public int getId() {
		return delegate.getId();
	}
	@Override
	public void delete() {
		delegate.delete();
	}
	@Override
	public Component component(Class<? extends Component> component) {
		return delegate.component(component);
	}
	@Override
	public void add(Component component) {
		delegate.add(component);
	}
	@Override
	public void add(Component... components) {
		delegate.add(components);
	}
	@Override
	public void update(Component component) {
		delegate.update(component);
	}
	@Override
	public void deleteComponent(Class<? extends Component> component) {
		delegate.deleteComponent(component);
	}
	@Override
	public Node create(List<Component> components) {
		return delegate.create(components);
	}
	@Override
	public Node create(Component... components) {
		return delegate.create(components);
	}
	@Override
	public Node asNode(int entity) {
		return delegate.asNode(entity);
	}
}
