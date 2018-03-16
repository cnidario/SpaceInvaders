package com.game.invaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.game.invaders.Invader.InvaderState;

public class SpaceInvaders extends ApplicationAdapter {
	private SpriteBatch batch;
	private long lastTime = 0;
	private List<Invader> enemies;
	private Player player;
	private Set<Shoot> alive_shoots;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		enemies = new ArrayList<Invader>();
		alive_shoots = new HashSet<Shoot>();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				enemies.add(new Invader(j*96, -i*76, 0, 0));
			}
		}
		player = new Player(this);
	}
	
	public void shoot(int x, int y) {
		alive_shoots.add(new Shoot(x, y));
	}
	
	private Map<Invader, Shoot> checkImpacts() {
		Map<Invader, Shoot> impacts = new HashMap<Invader, Shoot>();
		for(Invader enemy : enemies) {
			if(enemy.getState() != InvaderState.ALIVE)
				continue;
			Vector2 enemyPos = new Vector2(enemy.getX(), enemy.getY());
			for(Shoot shoot : alive_shoots) {
				Vector2 shootPos = new Vector2(shoot.getxPos(), shoot.getyPos());
				if(Collision.test(shootPos, Shoot.BBOX, enemyPos, Invader.BBOX)) {
					//asumo que no puede haber un disparo que de a dos invaders ni un invader simultaneamente alcanzado por 2,
					//o necesitaría listas XXX
					impacts.put(enemy, shoot);
					break;
				}
			}
		}
		return impacts;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		int elapsedTime;
		long currentTime = System.nanoTime();
		if(lastTime == 0) elapsedTime = 0;
		else elapsedTime = (int) ((currentTime - lastTime)/1000000);
		lastTime = currentTime;
		
		player.handleInput();
		for(Invader enemy : enemies) {
			enemy.update(elapsedTime);
		}
		List<Shoot> dead_shoots = new ArrayList<Shoot>(); 
		for(Shoot s : alive_shoots) {
			s.update(elapsedTime);
			if(s.getyPos() < Player.YSHOOT_MIN)
				dead_shoots.add(s);
		}
		Map<Invader, Shoot> impacts = checkImpacts();
		for(Invader enemy : impacts.keySet()) {
			dead_shoots.add(impacts.get(enemy)); //peligro double add XXX
			enemy.setState(InvaderState.DYING);
		}
		for(Shoot s : dead_shoots)
			alive_shoots.remove(s);
		List<Invader> dead_enemies = new ArrayList<Invader>();
		for(Invader enemy : enemies) {
			if(enemy.getState() == InvaderState.DIED)
				dead_enemies.add(enemy);
		}
		for (Invader enemy : dead_enemies) {
			enemies.remove(enemy);
		}
		
		player.update(elapsedTime);
		
		batch.begin();
		for(Shoot s : alive_shoots) {
			s.render(batch);
		}
		for(Invader enemy : enemies) {
			enemy.render(batch);
		}
		player.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
