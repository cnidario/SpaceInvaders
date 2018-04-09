package com.game.engine.system.process;

public abstract class AbstractProcess implements Process {
	private boolean alive = true;
	@Override
	public void kill() { alive = false; }
	@Override
	public boolean isAlive() { return alive; }
	@Override
	public void restart() { alive = true; }
	@Override
	public void init() {}
	@Override
	public abstract void update(float dt);
}
