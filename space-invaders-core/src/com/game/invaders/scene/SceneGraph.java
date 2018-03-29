package com.game.invaders.scene;

public class SceneGraph {
	private SceneNode root;

	public SceneGraph() {
		super();
		root = new SceneNode(null);
	}
	public SceneNode getRoot() {
		return root;
	}
	public boolean isEmpty() {
		return root.getData() == null;
	}
}
