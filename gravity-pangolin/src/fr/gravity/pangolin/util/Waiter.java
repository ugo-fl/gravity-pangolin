package fr.gravity.pangolin.util;

import java.util.Date;

public class Waiter {

	private final int waitingPeriod;
	private long timestamp = 0;
	private boolean started;
	
	public Waiter(int waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}
	
	public void start() {
		this.timestamp = new Date().getTime();
		this.started = true;
	}
	
	public boolean isStarted() {
		return started;
	}
	
	public boolean waitForIt() {
		if (new Date().getTime() - timestamp < waitingPeriod)
			return false;
		started = false;
		return true;
	}
	
}
