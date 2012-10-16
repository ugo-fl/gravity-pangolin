package fr.gravity.pangolin.helper;

import java.util.Date;

public class CountDownHelper {

	private final int duration;
	private long startTimestamp = 0;
	private boolean started;
	
	/**
	 * 
	 * @param waitingPeriod in milliseconds
	 */
	public CountDownHelper(int waitingPeriod) {
		this.duration = waitingPeriod;
	}
	
	public void start() {
		this.startTimestamp = new Date().getTime();
		this.started = true;
	}
	
	public boolean isStarted() {
		return started;
	}
	
	public boolean waitForIt() {
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
	
}
