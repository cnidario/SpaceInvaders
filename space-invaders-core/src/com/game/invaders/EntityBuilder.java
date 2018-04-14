package com.game.invaders;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.component.Animation;
import com.game.engine.entity.component.Collision;
import com.game.engine.entity.component.Group;
import com.game.engine.entity.component.GroupParent;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.Renderable;
import com.game.engine.entity.component.UserControlled;
import com.game.engine.system.collision.BoundingBox;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.event.types.EntityAddedEvent;
import com.game.invaders.component.Invader;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.component.TiltExploding;
import com.game.invaders.component.Invader.InvaderStateID;
import com.game.invaders.component.PlayerShip.PlayerState;
import com.game.invaders.data.GameResources;
import com.game.invaders.system.impact.CollisionGroup;

public class EntityBuilder {
	private Map<Class<? extends Component>, Component> comps;
	
	public EntityBuilder() {
		comps = new HashMap<Class<? extends Component>, Component>();
	}
	public EntityBuilder baseInvader() {
		return 	renderable(GameResources.INVADER.INVADERS[0][0])
				.collision(GameResources.INVADER.BBOX, 
						EnumSet.of(CollisionGroup.PLAYER_SHOOT),
						EnumSet.of(CollisionGroup.INVADER))
				.position(new Vector2())
				.invader(InvaderStateID.ALIVE);
	}
	
	public EntityBuilder animation(TextureRegion[] sprites, float duration, boolean loop) {
		comps.put(Animation.class, new Animation(sprites, duration, loop));
		return this;
	}
	public <E extends Enum<E>> EntityBuilder collision(BoundingBox boundingBox, EnumSet<E> collidesWith,
			EnumSet<E> collisionCategories) {
		comps.put(Collision.class, new Collision<E>(boundingBox, collidesWith, collisionCategories));
		return this;
	}
	public EntityBuilder group() {
		comps.put(Group.class, new Group());
		return this;
	}
	public EntityBuilder groupParent(Vector2 pos, int group) {
		comps.put(GroupParent.class, new GroupParent(pos, group));
		return this;
	}
	public EntityBuilder invader(InvaderStateID stateId) {
		comps.put(Invader.class, new Invader(stateId));
		return this;
	}
	public EntityBuilder motion(Vector2 speed) {
		comps.put(Motion.class, new Motion(speed));
		return this;
	}
	public EntityBuilder playerShip(PlayerState state, float shootDelay) {
		comps.put(PlayerShip.class, new PlayerShip(state, shootDelay));
		return this;
	}
	public EntityBuilder position(Vector2 pos) {
		comps.put(Position.class, new Position(pos));
		return this;
	}
	public EntityBuilder renderable(TextureRegion tex) {
		comps.put(Renderable.class, new Renderable(tex));
		return this;
	}
	public EntityBuilder tiltExploding(float tilt, float period) {
		comps.put(TiltExploding.class, new TiltExploding(tilt, period));
		return this;
	}
	public EntityBuilder userControlled() {
		comps.put(UserControlled.class, new UserControlled());
		return this;
	}
	
	public int build(EntityManager manager, EventSystem eventManager) {
		int entity = manager.createEntity();
		for (Component c : comps.values()) {
			manager.addComponent(entity, c);
		}
		eventManager.queueEvent(new EntityAddedEvent(entity));
		return entity;
	}
}
