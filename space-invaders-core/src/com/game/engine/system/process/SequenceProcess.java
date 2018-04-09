package com.game.engine.system.process;

import java.util.List;

public class SequenceProcess extends AbstractProcess {
	private List<AbstractProcess> processes;
	private int current_process;
	
	public SequenceProcess(List<AbstractProcess> processes) {
		assert processes.size() > 0;
		this.processes = processes;
		current_process = 0;
	}
	@Override
	public void restart() {
		for(Process p : processes) p.restart();
		super.restart();
	}
	@Override
	public void update(float dt) {
		assert current_process < processes.size();
		Process p;
		if((p = processes.get(current_process)).isAlive()) {
			p.update(dt);
		} else {
			if(current_process + 1 >= processes.size()) {
				kill();
			} else {
				current_process++;
				update(dt);
			}
		}
	}
	@Override
	public void init() {
	}
}
