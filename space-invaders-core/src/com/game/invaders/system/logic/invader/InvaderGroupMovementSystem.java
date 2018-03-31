package com.game.invaders.system.logic.invader;

import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.system.engine.ComponentPredicate;
import com.game.invaders.system.engine.PredicateParser;
import com.game.invaders.system.process.AbstractProcess;

public class InvaderGroupMovementSystem extends AbstractProcess {
	public static final ComponentPredicate p = PredicateParser.parse("InvaderGroupC");
	
	private EntityManager entityManager;
	
	public InvaderGroupMovementSystem(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	@Override
	public void update(float dt) {
	}

}
