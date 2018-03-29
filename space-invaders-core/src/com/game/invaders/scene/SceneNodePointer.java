package com.game.invaders.scene;

import java.util.ArrayList;
import java.util.List;

public class SceneNodePointer {
	private SceneGraph tree;
	private Object data;
	private SceneNodePointer parent;
	private List<SceneNodePointer> children;
	
	public SceneNodePointer(SceneGraph tree, Object data, SceneNodePointer parent, List<SceneNodePointer> children) {
		super();
		this.tree = tree;
		this.data = data;
		this.parent = parent;
		this.children = children;
	}
	public SceneNodePointer(SceneGraph tree, Object data, SceneNodePointer parent) {
		this(tree, data, parent, new ArrayList<SceneNodePointer>());
	}
	public SceneGraph getTree() {
		return tree;
	}
	public void setTree(SceneGraph tree) {
		this.tree = tree;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public SceneNodePointer getParent() {
		return parent;
	}
	public void setParent(SceneNodePointer parent) {
		this.parent = parent;
	}
	public List<SceneNodePointer> getChildren() {
		return children;
	}
	public void setChildren(List<SceneNodePointer> children) {
		this.children = children;
	}
	
	public boolean isRoot() {
		return tree.getRoot() == this;
	}
	public SceneNodePointer duplicate() {
		return new SceneNodePointer(tree, data, parent, children);
	}
	public SceneNodePointer addChild(Object data) {
		SceneNodePointer child = new SceneNodePointer(tree, data, this);
		children.add(child);
		return child;
	}
	/**
	 * Borra un nodo del scene graph, devuelve el padre
	 * @return El padre del nodo borrado.
	 */
	public SceneNodePointer remove() {
		parent.getChildren().remove(this);
		return parent;
	}
	public SceneNodePointer find(Object data) {
		if(data == this.data)
			return this;
		else {
			for (SceneNodePointer child : children) {
				SceneNodePointer result = child.find(data);
				if(result != null)
					return result;
			}
			return null;
		}
	}
}
