package com.game.invaders.scene;

import java.util.ArrayList;
import java.util.List;

public class NodeGroup implements SceneNode {
	public static final NodeGroup ROOT_PARENT = new NodeGroup(null);
	
	private SceneNode parent;
	private List<SceneNode> children;
	
	public NodeGroup(SceneNode parent) {
		super();
		this.parent = parent;
		children = new ArrayList<SceneNode>();
	}
	public List<SceneNode> children() {
		return children;
	}
	@Override
	public SceneNode parent() {
		return parent;
	}
	@Override
	public boolean isRoot() {
		return this == ROOT_PARENT;
	}
	@Override
	public void setParent(SceneNode parent) {
		this.parent = parent;
	}
}
