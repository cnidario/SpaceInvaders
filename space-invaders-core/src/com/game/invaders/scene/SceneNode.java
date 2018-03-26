package com.game.invaders.scene;

import java.util.List;

public interface SceneNode {
	void setParent(SceneNode parent);
	SceneNode parent();
	boolean isRoot();
	List<SceneNode> children();
}
