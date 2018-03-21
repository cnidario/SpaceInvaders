package com.game.invaders.subsystem.process;

import java.util.ArrayList;
import java.util.List;

import com.game.invaders.subsystem.event.EventManager;

public class ProcessManager {
	private List<Process> processes;
	private EventManager event_manager;
	public ProcessManager(EventManager event_manager) {
		processes = new ArrayList<Process>();
		this.event_manager = event_manager;
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
	public void init() {
	}
}
