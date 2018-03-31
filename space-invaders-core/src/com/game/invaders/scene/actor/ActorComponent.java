package com.game.invaders.scene.actor;

import java.util.HashMap;
import java.util.Map;

import com.game.invaders.scene.actor.components.ChildOfGroupC;
import com.game.invaders.scene.actor.components.CollisionActorC;
import com.game.invaders.scene.actor.components.ControllerC;
import com.game.invaders.scene.actor.components.GroupC;
import com.game.invaders.scene.actor.components.InvaderStateC;
import com.game.invaders.scene.actor.components.PhysicsActorC;
import com.game.invaders.scene.actor.components.PlayerShipStateC;
import com.game.invaders.scene.actor.components.PositionActorC;
import com.game.invaders.scene.actor.components.RenderableActorC;

public abstract class ActorComponent  {
	public enum ActorComponentID {
		POSITION,
		RENDERABLE,
		COLLISION,
		CONTROLLER,
		PHYSICS,
		INVADER_STATE,
		PLAYER_STATE,
		GROUP_CHILD,
		GROUP
	}
	public static final Map<ActorComponentID, Class<? extends ActorComponent>> COMPONENTS_MAP = createComponentsMap();
	private static Map<ActorComponentID, Class<? extends ActorComponent>> createComponentsMap() {
		Map<ActorComponentID, Class<? extends ActorComponent>> result = new HashMap<ActorComponent.ActorComponentID, Class<? extends ActorComponent>>();
		result.put(ActorComponentID.POSITION, PositionActorC.class);
		result.put(ActorComponentID.RENDERABLE, RenderableActorC.class);
		result.put(ActorComponentID.COLLISION, CollisionActorC.class);
		result.put(ActorComponentID.CONTROLLER, ControllerC.class);
		result.put(ActorComponentID.PHYSICS, PhysicsActorC.class);
		result.put(ActorComponentID.INVADER_STATE, InvaderStateC.class);
		result.put(ActorComponentID.PLAYER_STATE, PlayerShipStateC.class);
		result.put(ActorComponentID.GROUP, GroupC.class);
		result.put(ActorComponentID.GROUP_CHILD, ChildOfGroupC.class);
		return result;
	}
	public abstract ActorComponentID getID();
}
