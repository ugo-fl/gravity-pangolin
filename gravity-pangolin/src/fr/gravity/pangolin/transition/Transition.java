package fr.gravity.pangolin.transition;

import fr.gravity.pangolin.helper.CountDownHelper;
import fr.gravity.pangolin.screen.IScreen;

public abstract class Transition {

	protected CountDownHelper countDownHelper;

	/**
	 * 
	 * @param duration in milliseconds
	 */
	public Transition(int duration) {
		countDownHelper = new CountDownHelper(duration);
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
		return countDownHelper.waitForIt();
	}

}