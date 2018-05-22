package com.game.engine.node.spaces;

import com.game.engine.component.node.Space;
import com.game.engine.node.Node;
import com.game.engine.node.impl.NodeDecorator;

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
