package com.game.engine.scene;

import java.util.ArrayList;
import java.util.List;

public class SceneNode {
	private Object data;
	private List<SceneNode> children;
	
	public SceneNode(Object data, List<SceneNode> children) {
		super();
		this.data = data;
		this.children = children;
	}
	public SceneNode(Object data) {
		this(data, new ArrayList<SceneNode>());
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public List<SceneNode> getChildren() {
		return children;
	}
	public void setChildren(List<SceneNode> children) {
		this.children = children;
	}
}
