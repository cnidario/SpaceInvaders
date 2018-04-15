package com.game.invaders.factory;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.event.EventSystem;
import com.game.invaders.EntityBuilder;
import com.game.invaders.component.Invader.InvaderStateID;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;
import com.game.invaders.system.impact.CollisionGroup;

public class InvaderFactory {
	private EntityManager manager;
	private EventSystem eventSystem;
	private EntityBuilderFactory entityBuilderFactory;
	
	public InvaderFactory(EntityManager manager, EventSystem eventSystem, EntityBuilderFactory entityBuilderFactory) {
		super();
		this.manager = manager;
		this.eventSystem = eventSystem;
		this.entityBuilderFactory = entityBuilderFactory;
	}
	private EntityBuilder baseInvader() {
		return entityBuilderFactory.create()
				.renderable(GameResources.INVADER.INVADERS[0][0])
				.collision(GameResources.INVADER.BBOX, 
						EnumSet.noneOf(CollisionGroup.class), //collides with none
						EnumSet.of(CollisionGroup.INVADER)) //collision categories
				.position(new Vector2())
				.invader(InvaderStateID.ALIVE);
	}
	public int create(Vector2 pos, int type, int invaderGroup) {
		return baseInvader()
				.animation(GameResources.INVADER.INVADERS[type],
						GameConfigData.INVADER.ANIM_TIME, true)
				.groupParent(pos, invaderGroup)
				.build(manager, eventSystem);
	}
}
