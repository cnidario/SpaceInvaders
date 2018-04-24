package com.game.engine.system.entity.node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
import com.game.engine.system.entity.node.component.RemoveComponent;
import com.game.engine.system.entity.node.component.RemoveEntity;

public class NodeSetManager {
	private Map<Class<? extends Component>, List<EntityNodeSet>> compMappings;
	private Map<EntityNodeSet, List<Class<? extends Component>>> inverseCompMappings;
	
	public NodeSetManager() {
		super();
		compMappings = new HashMap<Class<? extends Component>, List<EntityNodeSet>>();
		inverseCompMappings = new HashMap<EntityNodeSet, List<Class<? extends Component>>>();
	}
	
	public EntityNodeSet createNodeSet(Class<? extends Component>... comps) {
		Set<Class<? extends Component>> cs = new HashSet<Class<? extends Component>>();
		for (Class<? extends Component> c : cs) {
			cs.add(c);
		}
		return createNodeSet(cs);
	}
	public EntityNodeSet createNodeSet(Set<Class<? extends Component>> comps) {
		EntityNodeSet nodeSet = new EntityNodeSet();
		inverseCompMappings.put(nodeSet, new ArrayList<Class<? extends Component>>(comps));
		for (Class<? extends Component> clazz : comps) {
			List<EntityNodeSet> l = compMappings.get(clazz);
			if(l == null)
				l = new ArrayList<EntityNodeSet>();
			l.add(nodeSet);
		}
		return nodeSet;
	}
}
