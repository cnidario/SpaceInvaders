package com.game.engine.entity;

import java.util.HashMap;
import java.util.Map;
import com.game.engine.entity.component.Animation;
import com.game.engine.entity.component.Collision;
import com.game.engine.entity.component.Group;
import com.game.engine.entity.component.GroupParent;
import com.game.engine.entity.component.Motion;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.Renderable;
import com.game.engine.entity.component.UserControlled;
import com.game.invaders.component.Invader;
import com.game.invaders.component.PlayerShip;
import com.game.invaders.component.TiltExploding;

public abstract class Component  {
	public enum ComponentID {
		POSITION,
		RENDERABLE,
		COLLISION,
		USER_CONTROLLED,
		MOTION,
		INVADER,
		PLAYER_SHIP,
		GROUP_PARENT,
		GROUP,
		ANIMATION,
		TILT_EXPLODING
	}
	public static final Map<ComponentID, Class<? extends Component>> COMPONENTS_MAP = createComponentsMap();
	private static Map<ComponentID, Class<? extends Component>> createComponentsMap() {
		Map<ComponentID, Class<? extends Component>> result = new HashMap<Component.ComponentID, Class<? extends Component>>();
		result.put(ComponentID.POSITION, Position.class);
		result.put(ComponentID.RENDERABLE, Renderable.class);
		result.put(ComponentID.COLLISION, Collision.class);
		result.put(ComponentID.USER_CONTROLLED, UserControlled.class);
		result.put(ComponentID.MOTION, Motion.class);
		result.put(ComponentID.INVADER, Invader.class);
		result.put(ComponentID.PLAYER_SHIP, PlayerShip.class);
		result.put(ComponentID.GROUP, Group.class);
		result.put(ComponentID.GROUP_PARENT, GroupParent.class);
		result.put(ComponentID.ANIMATION, Animation.class);
		result.put(ComponentID.TILT_EXPLODING, TiltExploding.class);
		return result;
	}
	public abstract ComponentID getID();
}
