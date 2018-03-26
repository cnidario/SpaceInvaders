package com.game.invaders.scene;

public class SceneGraph {
	private SceneNode root;

	public SceneGraph(SceneNode root) {
		super();
		this.root = root;
	}
	public SceneNode getRoot() {
		return root;
	}
	public void setRoot(SceneNode root) {
		this.root = root;
	}
}
