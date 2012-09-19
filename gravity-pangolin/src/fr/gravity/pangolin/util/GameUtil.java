package fr.gravity.pangolin.util;

import com.badlogic.gdx.math.Rectangle;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.screen.AbstractScreen;

public class GameUtil {

	public static AbstractScreen getScreen() {
		return (AbstractScreen) GravityPangolinGame.getInstance().getScreen();
	}

	public static float projectCoordinateX(float x) {
		return -getScreen().getWidth() / 2 + x;
	}

	public static float projectCoordinateY(float y) {
		return -getScreen().getHeight() / 2 + y;
	}

	public static boolean isOutOfScreen(Rectangle rectangle) {
		AbstractScreen screen = GameUtil.getScreen();
		
		final float DEEP_SPACE_MARGIN = screen.getWidth() / 20;
		
		float x = rectangle.getX();
		float y = rectangle.getY();
		float width = rectangle.getWidth();
		float height = rectangle.getHeight();
		
		if (x < (-screen.getWidth() / 2) - width - DEEP_SPACE_MARGIN || x > (screen.getWidth() / 2) + DEEP_SPACE_MARGIN)
			return true;
		if (y < (-screen.getHeight() / 2) - height - DEEP_SPACE_MARGIN || y > (screen.getHeight() / 2) + DEEP_SPACE_MARGIN)
			return true;
		
		return false;
	}
	
}
