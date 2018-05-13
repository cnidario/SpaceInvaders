package com.game.engine.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.game.engine.entity.Component;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;

/**
 * Sistema que guarda asociaciones de componentes, de modo que se asignen automáticamente otros
 * ShortLife -> inmediatemente se le asigna el delete
 */
public class ComponentMapperSystem extends AbstractProcess {
	class Pair {
		NodeSet nodeSet;
		List<Component> components;
		public Pair(NodeSet nodeSet) {
			this.nodeSet = nodeSet;
			components = new ArrayList<Component>();
		}
	}
	private EntityNodeSetFactory entityNodeSetFactory;
	private Map<Class<? extends Component>, Pair> nodeSets; 
	
	public ComponentMapperSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		nodeSets = new HashMap<Class<? extends Component>, Pair>();
		this.entityNodeSetFactory = entityNodeSetFactory;
	}
	@SuppressWarnings("unchecked")
	public void addMapping(Class<? extends Component> clazz, Component component) {
		Pair p = nodeSets.get(clazz);
		if(p == null) {
			NodeSet ns = entityNodeSetFactory.create(clazz);
			p = new Pair(ns);
			nodeSets.put(clazz, p);
		}
		p.components.add(component);
	}
	@Override
	public void update(float dt) {
		for (Class<? extends Component> clazz : nodeSets.keySet()) {
			Pair p = nodeSets.get(clazz);
			for(Node node : p.nodeSet) {
				for (Component c : p.components) {
					node.add(c);
				}
			}
		}
	}

}
