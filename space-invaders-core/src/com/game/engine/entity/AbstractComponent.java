package com.game.engine.entity;

public abstract class AbstractComponent extends Component {
	private final ComponentID type;
	public AbstractComponent(ComponentID type) {
		this.type = type;
	}
	@Override
	public ComponentID getID() { return type; }
}
