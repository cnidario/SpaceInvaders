package com.game.engine.system.entity;

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
import com.game.engine.entity.component.Impact;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.Renderable;
import com.game.engine.entity.component.UserControlled;
import com.game.engine.system.collision.BoundingBox;
import com.game.engine.system.entity.node.Node;
import com.game.invaders.component.Invader;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.component.TiltExploding;
import com.game.invaders.component.Invader.InvaderStateID;
import com.game.invaders.component.InvaderImpact;
import com.game.invaders.component.PlayerShip.PlayerState;

public class EntityBuilder {
	private EntityManager entityManager;
	private Map<Class<? extends Component>, Component> comps;
	
	public EntityBuilder(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
		comps = new HashMap<Class<? extends Component>, Component>();
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
	public EntityBuilder impact(Node e1, Node e2) {
		comps.put(Impact.class, new Impact(e1.getId(), e2.getId()));
		return this;
	}
	public EntityBuilder invaderImpact(int invader, float time) {
		comps.put(InvaderImpact.class, new InvaderImpact(invader, time));
		return this;
	}
	
	public int build() {
		int entity = entityManager.createEntity();
		for (Component c : comps.values()) {
			entityManager.addComponent(entity, c);
		}
		return entity;
	}
}
