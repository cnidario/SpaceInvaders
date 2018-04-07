package com.game.invaders.scene.actor;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.math.Vector2;
import com.game.invaders.GameConfigData;
import com.game.invaders.GameResources;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.component.AnimationSpriteC;
import com.game.invaders.scene.actor.component.ChildOfGroupC;
import com.game.invaders.scene.actor.component.CollisionActorC;
import com.game.invaders.scene.actor.component.InvaderStateC;
import com.game.invaders.scene.actor.component.PositionActorC;
import com.game.invaders.scene.actor.component.RenderableActorC;
import com.game.invaders.scene.actor.component.CollisionActorC.CollisionGroup;
import com.game.invaders.scene.actor.component.InvaderStateC.InvaderStateID;
import com.game.invaders.system.event.Event;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.event.types.ActorLifeCycleEvent;

public class InvaderBuilder {
	private Map<ActorComponentID, ActorComponent> comps;
	private InvaderBuilder() {
		comps = new HashMap<ActorComponent.ActorComponentID, ActorComponent>();
	}
	
	public static InvaderBuilder create() {
		InvaderBuilder builder = new InvaderBuilder();
		builder.comps.put(ActorComponentID.RENDERABLE, new RenderableActorC(GameResources.INVADER.INVADERS[0][0]));
		builder.comps.put(ActorComponentID.COLLISION, new CollisionActorC(GameResources.INVADER.BBOX, 
						EnumSet.of(CollisionGroup.PLAYER_SHOOT), EnumSet.of(CollisionGroup.INVADER)));
		builder.comps.put(ActorComponentID.POSITION, new PositionActorC(new Vector2()));
		builder.comps.put(ActorComponentID.INVADER_STATE, new InvaderStateC(InvaderStateID.ALIVE));
		return builder;
	}
	public InvaderBuilder num(int num) {
		comps.put(ActorComponentID.ANIMATION, new AnimationSpriteC(GameResources.INVADER.INVADERS[num], GameConfigData.INVADER.ANIM_TIME, true));
		return this;
	}
	public InvaderBuilder intoGroup(Vector2 pos, int group) {
		comps.put(ActorComponentID.GROUP_CHILD, new ChildOfGroupC(pos, group));
		return this;
	}
	public int build(EntityManager manager, EventManager eventManager) {
		int invader = manager.createEntity();
		for (ActorComponent c : comps.values()) {
			manager.addComponent(invader, c);
		}
		eventManager.queueEvent(new ActorLifeCycleEvent(Event.EventType.ACTOR_CREATED, invader));
		return invader;
	}
}
