package com.game.engine.system.render;

import java.util.EnumSet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.EntityManager;
import com.game.engine.entity.Component.ComponentID;
import com.game.engine.entity.component.Position;
import com.game.engine.entity.component.Renderable;
import com.game.engine.system.EntityMapper;
import com.game.engine.system.event.EventSystem;
import com.game.engine.system.process.AbstractProcess;

public class RenderSystem extends AbstractProcess {
	private SpriteBatch batch;
	private EntityManager manager;
	private EventSystem eventManager;
	private EntityMapper managedEntities;
	
	public RenderSystem(EntityManager manager, EventSystem eventManager) {
		super();
		batch = new SpriteBatch();
		this.manager = manager;
		this.eventManager = eventManager;
		this.managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ComponentID.RENDERABLE, ComponentID.POSITION));
	}
	
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			Renderable render_c = (Renderable) manager.componentFor(e, ComponentID.RENDERABLE);
			Position pos_c = (Position) manager.componentFor(e, ComponentID.POSITION);
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
