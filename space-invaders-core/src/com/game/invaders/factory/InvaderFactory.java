package com.game.invaders.factory;

import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.event.EventSystem;
import com.game.invaders.EntityBuilder;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;

public class InvaderFactory {
	private EntityManager manager;
	private EventSystem eventSystem;
	
	public InvaderFactory(EntityManager manager, EventSystem eventSystem) {
		super();
		this.manager = manager;
		this.eventSystem = eventSystem;
	}
	public int create(Vector2 pos, int type, int invaderGroup) {
		return new EntityBuilder().baseInvader()
			.animation(GameResources.INVADER.INVADERS[type],
					GameConfigData.INVADER.ANIM_TIME, true)
			.groupParent(pos, invaderGroup)
			.build(manager, eventSystem);
	}
}
