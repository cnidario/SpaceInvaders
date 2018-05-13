package com.game.engine.system.render;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
		font = new BitmapFont();
		nodeSet = entityNodeSetFactory.create(TextRenderable.class, Position.class);
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
