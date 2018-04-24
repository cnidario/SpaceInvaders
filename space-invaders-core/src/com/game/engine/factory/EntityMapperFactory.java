package com.game.engine.factory;

import java.util.HashSet;
import java.util.Set;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.entity.EntityMapper;
import com.game.engine.system.event.EventSystem;

public class EntityMapperFactory {
	private EntityManager entityManager;
	private EventSystem eventSystem;
	
	public EntityMapperFactory(EntityManager entityManager, EventSystem eventSystem) {
		super();
		this.entityManager = entityManager;
		this.eventSystem = eventSystem;
	}
	public EntityMapper create(Class<? extends Component>... comps) {
		Set<Class<? extends Component>> cs = new HashSet<Class<? extends Component>>();
		for(Class<? extends Component> clazz : comps) {
			cs.add(clazz);
		}
		return new EntityMapper(entityManager, eventSystem, cs);
	}
}
