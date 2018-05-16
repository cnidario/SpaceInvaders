package com.game.engine.component;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
