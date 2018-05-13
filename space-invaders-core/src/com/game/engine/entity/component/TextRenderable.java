package com.game.engine.entity.component;

import com.game.engine.entity.Component;

public class TextRenderable implements Component {
	private String text;

	public TextRenderable(String text) {
		super();
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
