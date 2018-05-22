package com.game.engine.system.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.game.engine.component.ShipAction;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.node.Node;
import com.game.engine.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;

public class InputSystem extends AbstractProcess {
	private NodeSet nodeSet;
	private Node rootSpace;

	@SuppressWarnings("unchecked")
	public InputSystem(EntityNodeSetFactory entityNodeSetFactory, Node rootSpace) {
		super();
		this.rootSpace = rootSpace;
		nodeSet = entityNodeSetFactory.create(ShipAction.class);
	}
	private void processInput() {
		Node inputNode = nodeSet.one();
		ShipAction shipAction = (ShipAction) inputNode.component(ShipAction.class);
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			shipAction.setMoveLeft(true);
			shipAction.setMoveRight(false);
		} else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			shipAction.setMoveRight(true);
			shipAction.setMoveLeft(false);
		} else {
			shipAction.setMoveLeft(false);
			shipAction.setMoveRight(false);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
			shipAction.setShoot(true);
		else
			shipAction.setShoot(false);
	}
	@Override
	public void init() {
		rootSpace.create(new ShipAction());
	}
	@Override
	public void update(float dt) {
		processInput();
	}
}
