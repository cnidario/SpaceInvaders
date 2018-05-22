package com.game.invaders;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.factory.AnimationFactory;
import com.game.engine.node.Node;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;
import com.game.invaders.factory.InvaderFactory;
import com.game.invaders.factory.InvaderGroupFactory;
import com.game.invaders.factory.PlayerShipFactory;

public class GameWorld {
	private InvaderFactory invaderFactory;
	private InvaderGroupFactory invaderGroupFactory;
	private PlayerShipFactory playerShipFactory;
	private AnimationFactory animationFactory;
	private Node rootNode;
	
	public GameWorld(Node rootNode, InvaderFactory invaderFactory, InvaderGroupFactory invaderGroupFactory, PlayerShipFactory playerShipFactory, AnimationFactory animationFactory) {
		super();
		this.rootNode = rootNode;
		this.invaderFactory = invaderFactory;
		this.invaderGroupFactory = invaderGroupFactory;
		this.playerShipFactory = playerShipFactory;
		this.animationFactory = animationFactory;
	}
	public void init() {
		createInvaders();
		createPlayer();
	}
	private Node createInvaderAnimation(int type) {
		return animationFactory.create(rootNode, GameResources.INVADER.INVADERS[type], GameConfigData.INVADER.ANIM_TIME, true);
	}
	private void createInvaders() {
		Node invaderGroup = invaderGroupFactory.create(rootNode);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				int type = i % GameResources.INVADER.INVADERS.length;
				Node animation = createInvaderAnimation(type);
				invaderFactory.create(invaderGroup, animation, new Vector2(j*96, -i*76), type);
			}
		}
	}
	private Node createPlayer() {
		return playerShipFactory.create(rootNode);
	}
}
