package com.game.engine.entity.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.engine.entity.AbstractComponent;

public class Renderable extends AbstractComponent {
	private TextureRegion tex;
	
	public Renderable(TextureRegion tex) {
		super(ComponentID.RENDERABLE);
		this.tex = tex;
	}
	public TextureRegion getTex() {
		return tex;
	}
	public void setTex(TextureRegion tex) {
		this.tex = tex;
	}
}
