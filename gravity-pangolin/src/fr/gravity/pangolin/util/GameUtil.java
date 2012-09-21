package fr.gravity.pangolin.util;

import com.badlogic.gdx.math.Rectangle;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.screen.AbstractScreen2;

public class GameUtil {

	public static AbstractScreen2 getScreen() {
		return (AbstractScreen2) GravityPangolinGame.getInstance().getScreen();
	}

	public static float projectCoordinateX(float x) {
		return x * getScreen().getPpuX();
	}

	public static float projectCoordinateY(float y) {
		return y * getScreen().getPpuY();
	}

	public static boolean isOutOfScreen(Rectangle rectangle) {
		AbstractScreen2 screen = GameUtil.getScreen();

		final float DEEP_SPACE_MARGIN = screen.getWidth() / 20;

		float x = rectangle.getX();
		float y = rectangle.getY();
		float width = rectangle.getWidth();
		float height = rectangle.getHeight();

		if (x < -width - DEEP_SPACE_MARGIN || x > screen.getWidth() + DEEP_SPACE_MARGIN)
			return true;
		if (y < -height - DEEP_SPACE_MARGIN || y > screen.getHeight() + DEEP_SPACE_MARGIN)
			return true;

		return false;
	}
	
}
