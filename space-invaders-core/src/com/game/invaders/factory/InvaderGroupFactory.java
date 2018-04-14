package com.game.invaders.factory;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.event.EventSystem;
import com.game.invaders.EntityBuilder;
import com.game.invaders.data.GameConfigData;

public class InvaderGroupFactory {
	private EntityManager manager;
	private EventSystem eventSystem;
	
	public InvaderGroupFactory(EntityManager manager, EventSystem eventSystem) {
		super();
		this.manager = manager;
		this.eventSystem = eventSystem;
	}
	public int create() {
		return new EntityBuilder()
				.group()
				.position(new Vector2(GameConfigData.INVADER.MINX, 800))
				.motion(new Vector2(GameConfigData.INVADER.MINSPEED, 0))
				.build(manager, eventSystem);
	}
}
