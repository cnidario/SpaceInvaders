package com.game.invaders.actor;

import java.util.HashMap;
import java.util.Map;
import com.badlogic.gdx.math.Vector2;
import com.game.invaders.actor.ActorComponent.ActorComponentID;

public class Actor {
	private Vector2 pos = new Vector2();
	private boolean active = true;
	private boolean visible = true;
	private Map<ActorComponent.ActorComponentID, ActorComponent> components = new HashMap<ActorComponent.ActorComponentID, ActorComponent>();
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Vector2 getPos() {
		return pos;
	}
	public void setPos(Vector2 pos) {
		this.pos = pos;
	}
	public ActorComponent getComponent(ActorComponentID componentID) {
		return components.get(componentID);
	}
	public void addComponent(ActorComponent component) {
		components.put(component.getID(), component);
	}
}
