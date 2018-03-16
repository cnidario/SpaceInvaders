package com.game.invaders;

import com.badlogic.gdx.math.Vector2;

public class Collision {
	public static boolean test(Vector2 aPos, BoundingBox a, Vector2 bPos, BoundingBox b) {
		boolean horizontal_overlap = (aPos.x > bPos.x && aPos.x < bPos.x + b.getWidth()) ||
				(bPos.x > aPos.x && bPos.x < aPos.x + a.getWidth());
		boolean vertical_overlap = (aPos.y > bPos.y && aPos.y < bPos.y + b.getHeight()) ||
				(bPos.y > aPos.y && bPos.y < aPos.y + a.getHeight());
		return horizontal_overlap && vertical_overlap;
	}
}
