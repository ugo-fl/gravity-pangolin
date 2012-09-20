package fr.gravity.pangolin.util;

import com.badlogic.gdx.math.Rectangle;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.screen.AbstractScreen;
import fr.gravity.pangolin.screen.AbstractScreen2;

public class GameUtil {

	public static AbstractScreen2 getScreen() {
		return (AbstractScreen2) GravityPangolinGame.getInstance().getScreen();
	}

	public static float projectCoordinateX(float x) {
		return -getScreen().getWidth() / 2 + x;
	}

	public static float projectCoordinateY(float y) {
		return -getScreen().getHeight() / 2 + y;
	}

	public static boolean isOutOfScreen(Rectangle rectangle) {
		AbstractScreen2 screen = GameUtil.getScreen();
		
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
