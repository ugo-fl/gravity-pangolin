package fr.gravity.pangolin.util;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.ScreenAbstract;

public class GameUtil {

	private static ScreenAbstract screen;

	public static ScreenAbstract getScreen() {
		if (screen == null)
			screen = (ScreenAbstract) GravityPangolinGame.getInstance().getScreen();
		return screen;
	}

	public static float projectCoordinateX(float x) {
		return -screen.getWidth() / 2 + x;
	}

	public static float projectCoordinateY(float y) {
		return -(-screen.getHeight() / 2 + y);
	}
}
