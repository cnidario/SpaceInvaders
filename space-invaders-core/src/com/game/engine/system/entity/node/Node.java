package com.game.engine.system.entity.node;

import com.game.engine.entity.Component;

public interface Node {
	void delete();
	Component component(Class<? extends Component> component);
	Component update(Component component);
	void deleteComponent(Class<? extends Component> component);
}
