package com.game.engine.system.process;

public class Delay extends AbstractProcess {
	private int miliseconds;
	private int counter;
	public Delay(int miliseconds) {
		this.miliseconds = miliseconds;
		counter = 0;
	}
	@Override
	public void update(float dt) {
		counter += dt;
		if(counter > miliseconds) {
			kill();
		}
	}
	@Override
	public void restart() {
		super.restart();
		counter = 0;
	}
	@Override
	public void init() {
	}
}
