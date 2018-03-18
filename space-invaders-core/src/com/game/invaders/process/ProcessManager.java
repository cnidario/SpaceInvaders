package com.game.invaders.process;

import java.util.ArrayList;
import java.util.List;

public class ProcessManager {
	private List<Process> processes;
	public ProcessManager() {
		processes = new ArrayList<Process>();
	}
	public void addProcess(Process p) {
		processes.add(p);
	}
	public void update(float dt) {
		List<Process> clean_list = new ArrayList<Process>();
		for(Process process : processes) {
			if(process.isAlive())
				process.update(dt);
			else
				clean_list.add(process);
		}
		for (Process process : clean_list) {
			processes.remove(process);
		}
	}
}
