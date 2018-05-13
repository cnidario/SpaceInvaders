package com.game.engine.system.node.spaces;

import com.game.engine.system.node.Node;
import com.game.engine.system.node.component.Space;
import com.game.engine.system.node.impl.NodeDecorator;

public class SpacedNode extends NodeDecorator {
	public SpacedNode(Node delegate) {
		super(delegate);
	}
	public Node getSpace() {
		Space space_c = (Space) delegate.component(Space.class);
		if(space_c != null) {
			return delegate.asNode(space_c.getId());
		} else {
			return null;
		}
	}
}
