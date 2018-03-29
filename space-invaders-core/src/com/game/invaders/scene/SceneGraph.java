package com.game.invaders.scene;

public class SceneGraph {
	private SceneNodePointer root;

	public SceneGraph() {
		super();
		root = new SceneNodePointer(this, null, null);
	}
	public SceneNodePointer getRoot() {
		return root;
	}
	public boolean isEmpty() {
		return root.getData() == null;
	}
}
