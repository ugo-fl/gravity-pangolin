package fr.gravity.pangolin.transition;

import fr.gravity.pangolin.screen.IScreen;
import fr.gravity.pangolin.util.CountDown;

public abstract class Transition {

	protected CountDown countDownHelper;

	/**
	 * 
	 * @param duration in milliseconds
	 */
	public Transition(int duration) {
		countDownHelper = new CountDown(duration);
	}
	
	public void start() {
		countDownHelper.start();
	}

	/**
	 * @param current
	 *            The current game state.
	 * @param next
	 *            The next game state.
	 */
	public abstract void render(IScreen current, IScreen next);

	public boolean isFinished() {
		return countDownHelper.isFinished();
	}

}