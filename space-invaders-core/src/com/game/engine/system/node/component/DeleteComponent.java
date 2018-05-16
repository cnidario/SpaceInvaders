package com.game.engine.system.node.component;

import com.game.engine.component.Component;

public class DeleteComponent implements Component {
	private Class<? extends Component> clazz;
	
	public DeleteComponent(Class<? extends Component> clazz) {
		super();
		this.clazz = clazz;
	}
	public Class<? extends Component> getClazz() {
		return clazz;
	}
	public void setClazz(Class<? extends Component> clazz) {
		this.clazz = clazz;
	}
}
