package fr.gravity.pangolin.game;

import java.util.Date;

public class CountDown {

	private final int waitingPeriod;
	private long timestamp = 0;
	private boolean started;
	
	public CountDown(int waitingPeriod) {
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
