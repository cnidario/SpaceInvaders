package com.game.engine.system.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.Renderable;
import com.game.engine.factory.EntityNodeSetFactory;
import com.game.engine.system.node.Node;
import com.game.engine.system.node.NodeSet;
import com.game.engine.system.process.AbstractProcess;

public class RenderSystem extends AbstractProcess {
	private SpriteBatch batch;
	private NodeSet nodeSet;
	
	@SuppressWarnings("unchecked")
	public RenderSystem(EntityNodeSetFactory entityNodeSetFactory) {
		super();
		batch = new SpriteBatch();
		nodeSet = entityNodeSetFactory.create(Renderable.class, Position.class);
	}
	
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Node node : nodeSet) {
			Renderable render_c = (Renderable) node.component(Renderable.class);
			Position pos_c = (Position) node.component(Position.class);
			Vector2 pos = pos_c.getPos();
			batch.draw(render_c.getTex(), pos.x, pos.y);
		}
		batch.end();
	}
	@Override
	public void update(float dt) {
		render();
	}
}
