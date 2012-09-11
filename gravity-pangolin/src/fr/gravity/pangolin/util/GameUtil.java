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
}
