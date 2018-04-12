package com.game.engine.system.render;

import java.util.HashSet;
import java.util.Set;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.engine.entity.Component;
import com.game.engine.entity.EntityManager;
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
		Set<Class<? extends Component>> cs = new HashSet<Class<? extends Component>>();
		cs.add(Renderable.class);
		cs.add(Position.class);
		this.managedEntities = new EntityMapper(manager, eventManager, cs);
	}
	
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			Renderable render_c = (Renderable) manager.componentFor(e, Renderable.class);
			Position pos_c = (Position) manager.componentFor(e, Position.class);
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
