package fr.gravity.pangolin.util;

import java.util.Date;

public class CountDown {

	private final int duration;
	private long startTimestamp = 0;
	private boolean started;
	
	/**
	 * 
	 * @param waitingPeriod in milliseconds
	 */
	public CountDown(int waitingPeriod) {
		this.duration = waitingPeriod;
	}
	
	public void start() {
		this.startTimestamp = new Date().getTime();
		this.started = true;
	}
	
	public boolean isStarted() {
		return started;
	}
	
	public boolean isFinished() {
		if (new Date().getTime() - startTimestamp < duration)
			return false;
		started = false;
		return true;
	}
	
	public float getTimeRemaining() {
		return duration - (new Date().getTime() - startTimestamp);
	}
	
	public float getDuration() {
		return duration;
	}

	public void finish() {
		startTimestamp = new Date().getTime() - duration;
	}
	
}
