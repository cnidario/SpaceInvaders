package com.game.invaders.scene;

import java.util.Stack;

public class SceneNodePointer {
	private SceneNode node;
	private Stack<SceneNode> parents;
	
	public SceneNodePointer(SceneNode node, Stack<SceneNode> parents) {
		super();
		this.node = node;
		this.parents = parents;
	}
	public SceneNodePointer(SceneGraph tree) {
		this(tree.getRoot(), new Stack<SceneNode>());
	}
	public SceneNode getNode() {
		return node;
	}

	public boolean isRoot() {
		return node.getData() == null;
	}
	@SuppressWarnings("unchecked")
	public SceneNodePointer duplicate() {
		return new SceneNodePointer(node, (Stack<SceneNode>)parents.clone());
	}
	/**
	 * Modifica anterior como en Iterator
	 */
	public SceneNodePointer addChild(Object data) {
		SceneNode child = new SceneNode(data);
		node.getChildren().add(child);
		parents.push(node);
		node = child;
		return this;
	}
	/**
	 * Borra un nodo del scene graph, devuelve el padre
	 * @return El padre del nodo borrado.
	 */
	public SceneNodePointer remove() {
		SceneNode to_remove = node;
		node = parents.pop();
		node.getChildren().remove(to_remove);
		return this;
	}
	public SceneNodePointer find(Object data) {
		if(data == this.node.getData())
			return this;
		else {
			SceneNodePointer childPointer = duplicate();
			childPointer.parents.push(node);
			for (SceneNode childNode : node.getChildren()) {
				childPointer.node = childNode;
				SceneNodePointer resultPointer = childPointer.find(data);
				if(resultPointer != null)
					return resultPointer;
			}
			return null;
		}
	}
}
