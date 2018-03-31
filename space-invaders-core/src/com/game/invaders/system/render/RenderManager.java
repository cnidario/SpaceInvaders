package com.game.invaders.system.render;

import java.util.EnumSet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.IntSet.IntSetIterator;
import com.game.invaders.scene.actor.ActorComponent.ActorComponentID;
import com.game.invaders.scene.actor.EntityManager;
import com.game.invaders.scene.actor.components.PositionActorC;
import com.game.invaders.scene.actor.components.RenderableActorC;
import com.game.invaders.system.engine.EntityMapper;
import com.game.invaders.system.event.EventManager;
import com.game.invaders.system.process.AbstractProcess;

public class RenderManager extends AbstractProcess {
	private SpriteBatch batch;
	private EntityManager manager;
	private EventManager eventManager;
	private EntityMapper managedEntities;
	
	public RenderManager(EntityManager manager, EventManager eventManager) {
		super();
		batch = new SpriteBatch();
		this.manager = manager;
		this.eventManager = eventManager;
		this.managedEntities = new EntityMapper(manager, eventManager, EnumSet.of(ActorComponentID.RENDERABLE, ActorComponentID.POSITION));
	}
	
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (IntSetIterator iter = managedEntities.getGroup().iterator(); iter.hasNext; ) {
			int e = iter.next();
			RenderableActorC render_c = (RenderableActorC) manager.componentFor(e, ActorComponentID.RENDERABLE);
			PositionActorC pos_c = (PositionActorC) manager.componentFor(e, ActorComponentID.POSITION);
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
