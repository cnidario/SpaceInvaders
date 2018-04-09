package com.game.engine.entity;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.Component.ComponentID;
import com.game.engine.entity.component.Animation;
import com.game.engine.entity.component.Collision;
import com.game.engine.entity.component.Group;
import com.game.engine.entity.component.GroupParent;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.Renderable;
import com.game.engine.entity.component.UserControlled;
import com.game.engine.entity.component.Collision.CollisionGroup;
import com.game.engine.system.collision.BoundingBox;
import com.game.engine.system.event.Event;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.types.ActorLifeCycleEvent;
import com.game.invaders.component.Invader;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.component.TiltExploding;
import com.game.invaders.component.Invader.InvaderStateID;
import com.game.invaders.component.PlayerShip.PlayerState;
import com.game.invaders.data.GameResources;

public class EntityBuilder {
	private Map<ComponentID, Component> comps;
	private EntityBuilder() {
		comps = new HashMap<Component.ComponentID, Component>();
	}
	
	public static EntityBuilder create() {
		EntityBuilder builder = new EntityBuilder();
		return builder;
	}
	public static EntityBuilder baseInvader() {
		return create()
				.renderable(GameResources.INVADER.INVADERS[0][0])
				.collision(GameResources.INVADER.BBOX, 
						EnumSet.of(CollisionGroup.PLAYER_SHOOT),
						EnumSet.of(CollisionGroup.INVADER))
				.position(new Vector2())
				.invader(InvaderStateID.ALIVE);
	}
	
	public EntityBuilder animation(TextureRegion[] sprites, float duration, boolean loop) {
		comps.put(ComponentID.ANIMATION, new Animation(sprites, duration, loop));
		return this;
	}
	public EntityBuilder collision(BoundingBox boundingBox, EnumSet<CollisionGroup> collidesWith,
			EnumSet<CollisionGroup> collisionCategories) {
		comps.put(ComponentID.COLLISION, new Collision(boundingBox, collidesWith, collisionCategories));
		return this;
	}
	public EntityBuilder group() {
		comps.put(ComponentID.GROUP, new Group());
		return this;
	}
	public EntityBuilder groupParent(Vector2 pos, int group) {
		comps.put(ComponentID.GROUP_PARENT, new GroupParent(pos, group));
		return this;
	}
	public EntityBuilder invader(InvaderStateID stateId) {
		comps.put(ComponentID.INVADER, new Invader(stateId));
		return this;
	}
	public EntityBuilder motion(Vector2 speed) {
		comps.put(ComponentID.MOTION, new Motion(speed));
		return this;
	}
	public EntityBuilder playerShip(PlayerState state, float shootDelay) {
		comps.put(ComponentID.PLAYER_SHIP, new PlayerShip(state, shootDelay));
		return this;
	}
	public EntityBuilder position(Vector2 pos) {
		comps.put(ComponentID.POSITION, new Position(pos));
		return this;
	}
	public EntityBuilder renderable(TextureRegion tex) {
		comps.put(ComponentID.RENDERABLE, new Renderable(tex));
		return this;
	}
	public EntityBuilder tiltExploding(float tilt, float period) {
		comps.put(ComponentID.TILT_EXPLODING, new TiltExploding(tilt, period));
		return this;
	}
	public EntityBuilder userControlled() {
		comps.put(ComponentID.USER_CONTROLLED, new UserControlled());
		return this;
	}
	
	public int build(EntityManager manager, EventSystem eventManager) {
		int entity = manager.createEntity();
		for (Component c : comps.values()) {
			manager.addComponent(entity, c);
		}
		eventManager.queueEvent(new ActorLifeCycleEvent(Event.EventType.ACTOR_CREATED, entity));
		return entity;
	}
}
