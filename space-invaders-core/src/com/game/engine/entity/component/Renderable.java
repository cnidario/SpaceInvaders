package com.game.engine.entity.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.engine.entity.Component;

public class Renderable implements Component {
	private TextureRegion tex;
	
	public Renderable(TextureRegion tex) {
		this.tex = tex;
	}
	public TextureRegion getTex() {
		return tex;
	}
	public void setTex(TextureRegion tex) {
		this.tex = tex;
	}
}
