package com.game.engine.system.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.TextRenderable;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;

public class TextRenderSystem extends AbstractProcess {
	private BitmapFont font;
	private SpriteBatch batch;
	private NodeSet nodeSet;
	
	@SuppressWarnings("unchecked")
	public TextRenderSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		batch = new SpriteBatch();
		generateFonts();
		nodeSet = entityNodeSetFactory.create(TextRenderable.class, Position.class);
	}
	private void generateFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Roboto-Bold.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 24;
		font = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!
	}
	public void render() {
		batch.begin();
		for (Node node : nodeSet) {
			TextRenderable textrender_c = (TextRenderable) node.component(TextRenderable.class);
			Position pos_c = (Position) node.component(Position.class);
			Vector2 pos = pos_c.getPos();
			font.draw(batch, textrender_c.getText(), pos.x, pos.y);
		}
		batch.end();
	}
	@Override
	public void update(float dt) {
		render();
	}
}
