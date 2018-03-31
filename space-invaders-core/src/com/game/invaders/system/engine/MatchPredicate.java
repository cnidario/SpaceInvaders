package com.game.invaders.system.engine;

import java.util.Set;
import com.game.invaders.scene.actor.ActorComponent;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;

public class MatchPredicate implements ComponentPredicate {
	private ActorComponentID componentId;
	
	public MatchPredicate(ActorComponentID componentId) {
		super();
		this.componentId = componentId;
	}
	public ActorComponentID getComponentId() {
		return componentId;
	}
	public void setComponentId(ActorComponentID componentId) {
		this.componentId = componentId;
	}
	@Override
	public boolean match(Set<ActorComponent> components) {
		for (ActorComponent actorComponent : components) {
			if(actorComponent.getID() == componentId)
				return true;
		}
		return false;
	}
}
