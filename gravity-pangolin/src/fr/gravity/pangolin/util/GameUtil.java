package fr.gravity.pangolin.util;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.screen.ScreenAbstract;

public class GameUtil {

	public static ScreenAbstract getScreen() {
		return (ScreenAbstract) GravityPangolinGame.getInstance().getScreen();
	}

	public static float projectCoordinateX(float x) {
		return -getScreen().getWidth() / 2 + x;
	}

	public static float projectCoordinateY(float y) {
		return -getScreen().getHeight() / 2 + y;
	}

	public static boolean isOutOfScreen(float x, float y) {
		ScreenAbstract screen = getScreen();
		if (x < -screen.getWidth() / 2 || x > screen.getWidth() / 2
				|| y < -screen.getHeight() / 2 || y > screen.getHeight() / 2)
			return true;
		return false;
	}
}
