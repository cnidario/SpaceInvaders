package com.game.invaders;

import java.util.Arrays;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.invaders.animation.Animation;
import com.game.invaders.animation.AnimationPlayer;
import com.game.invaders.scene.actor.Actor;
import com.game.invaders.scene.actor.components.CollisionActorC;
import com.game.invaders.scene.actor.components.ControllerC;
import com.game.invaders.scene.actor.components.RenderableActorC;
import com.game.invaders.system.collision.BoundingBox;
import com.game.invaders.system.collision.CollisionManager;
import com.game.invaders.system.render.AnimationRenderStrategy;
import com.game.invaders.system.render.RenderStrategy;
import com.game.invaders.system.render.SpriteRenderStrategy;

public class GameResources {
	public static class PLAYER {
		public final static Texture IMAGE = new Texture("ship.png");
		public final static TextureRegion PLAYER = new TextureRegion(IMAGE);
		public final static BoundingBox BBOX = new BoundingBox(IMAGE.getWidth(), IMAGE.getHeight());
		public final static RenderStrategy RENDER_ST = new SpriteRenderStrategy(PLAYER);
		public static RenderableActorC RENDER_COMPO(Actor actor) {
			return new RenderableActorC(actor, RENDER_ST);
		}
		public static ControllerC CONTROLLER_COMPO(Actor actor) {
			return new ControllerC(actor);
		}
	}
	public static class INVADER {
		public final static Texture IMAGE =  new Texture("invader1.png"); 
		public final static TextureRegion[] INVADERS = { new TextureRegion(IMAGE, 64, 64), new TextureRegion(IMAGE, 64, 0, 64, 64)};
		public final static Animation MOVING_ANIM = new Animation(Arrays.asList(INVADERS), true);
		//uso una animación para todos, la misma, podría usar una independiente y que no se movieran sincronizados
		public final static AnimationPlayer MOVING_ANIM_PLAYER = new AnimationPlayer(MOVING_ANIM, 1200);
		public final static BoundingBox BBOX = new BoundingBox(IMAGE.getWidth(), IMAGE.getHeight());
		public final static RenderStrategy ANIM_RENDER_ST = new AnimationRenderStrategy(MOVING_ANIM_PLAYER);
		public static RenderableActorC RENDER_COMPO(Actor actor) {
			return new RenderableActorC(actor, ANIM_RENDER_ST);
		}
		public static CollisionActorC COLLISION_COMPO(Actor actor) {
			return new CollisionActorC(actor, BBOX, CollisionManager.INVADER_STRATEGY);
		}
	}
}
