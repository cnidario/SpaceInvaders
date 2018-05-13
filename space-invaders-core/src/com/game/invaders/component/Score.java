package com.game.invaders.component;

import com.game.engine.entity.Component;

public class Score implements Component {
	private int score;

	public Score(int score) {
		super();
		this.score = score;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
}
