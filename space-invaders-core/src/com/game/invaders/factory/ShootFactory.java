package com.game.invaders.factory;

import java.util.EnumSet;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.component.Collision;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.Renderable;
import com.game.engine.system.event.EventSystem;
import com.game.invaders.data.GameConfigData;
import com.game.invaders.data.GameResources;
import com.game.invaders.event.ShootCreatedEvent;
import com.game.invaders.system.impact.CollisionGroup;

public class ShootFactory {
	private EntityManager manager;
	private EventSystem eventSystem;
	
	public ShootFactory(EntityManager manager, EventSystem eventSystem) {
		super();
		this.manager = manager;
		this.eventSystem = eventSystem;
	}
	public int create(Vector2 pos) {
		int shoot = manager.createEntity();
		manager.addComponent(shoot, new Position(pos));
		manager.addComponent(shoot, new Collision<CollisionGroup>(GameResources.PLAYER.SHOOT_BBOX, EnumSet.of(CollisionGroup.INVADER), EnumSet.of(CollisionGroup.PLAYER_SHOOT)));
		manager.addComponent(shoot, new Renderable(GameResources.PLAYER.SHOOT));
		manager.addComponent(shoot, new Motion(new Vector2(0, GameConfigData.PLAYER.SHOOT_SPEED)));
		eventSystem.queueEvent(new ShootCreatedEvent(shoot));
		return shoot;
	}
}
