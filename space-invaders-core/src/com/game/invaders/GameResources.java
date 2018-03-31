package com.game.invaders;

import java.util.Arrays;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.invaders.animation.Animation;
import com.game.invaders.animation.AnimationPlayer;
import com.game.invaders.scene.actor.components.RenderableActorC;
import com.game.invaders.system.collision.BoundingBox;

public class GameResources {
	public static class PLAYER {
		public final static Texture IMAGE = new Texture("ship.png");
		public final static TextureRegion PLAYER = new TextureRegion(IMAGE);
		public final static BoundingBox BBOX = new BoundingBox(IMAGE.getWidth(), IMAGE.getHeight());
	}
	public static class INVADER {
		public final static Texture IMAGE =  new Texture("invader1.png"); 
		public final static TextureRegion[] INVADERS = { new TextureRegion(IMAGE, 64, 64), new TextureRegion(IMAGE, 64, 0, 64, 64)};
		public final static Animation MOVING_ANIM = new Animation(Arrays.asList(INVADERS), true);
		//uso una animación para todos, la misma, podría usar una independiente y que no se movieran sincronizados
		public final static AnimationPlayer MOVING_ANIM_PLAYER = new AnimationPlayer(MOVING_ANIM, 1200);
		public final static BoundingBox BBOX = new BoundingBox(IMAGE.getWidth(), IMAGE.getHeight());
		
	}
}
