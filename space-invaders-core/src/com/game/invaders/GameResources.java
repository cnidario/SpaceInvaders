package com.game.invaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.game.invaders.system.collision.BoundingBox;

public class GameResources {
	public static class PLAYER {
		public final static Texture IMAGE = new Texture("ship.png");
		public final static TextureRegion PLAYER = new TextureRegion(IMAGE);
		public final static BoundingBox BBOX = new BoundingBox(IMAGE.getWidth(), IMAGE.getHeight());
		
		public final static Texture SHOOT_IMG = new Texture("shoot.png");
		public final static TextureRegion SHOOT = new TextureRegion(SHOOT_IMG);
		public final static BoundingBox SHOOT_BBOX = new BoundingBox(SHOOT_IMG.getWidth(), SHOOT_IMG.getHeight());
	}
	public static class INVADER {
		public final static Texture IMAGE =  new Texture("invader1.png"); 
		public final static TextureRegion[][] INVADERS = { 
				 { new TextureRegion(IMAGE, 0, 0, 64, 64), new TextureRegion(IMAGE, 64, 0, 64, 64) },
				 { new TextureRegion(IMAGE, 0, 64, 64, 64), new TextureRegion(IMAGE, 64, 64, 64, 64) },
				 { new TextureRegion(IMAGE, 0, 128, 64, 64), new TextureRegion(IMAGE, 64, 128, 64, 64) },
				 { new TextureRegion(IMAGE, 0, 192, 64, 64), new TextureRegion(IMAGE, 64, 192, 64, 64) }
				};
		public final static BoundingBox BBOX = new BoundingBox(IMAGE.getWidth(), IMAGE.getHeight());
		
	}
	public static class GAME {
		public final static Sound[] HITS = {
				Gdx.audio.newSound(Gdx.files.internal("sound/hit1.wav")),
				Gdx.audio.newSound(Gdx.files.internal("sound/hit2.wav")),
				Gdx.audio.newSound(Gdx.files.internal("sound/hit3.wav"))
		};
	}
}
