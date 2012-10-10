package fr.gravity.pangolin.util;

import com.badlogic.gdx.math.Rectangle;

import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.screen.AbstractScreen;

public class GameUtil {

	public static AbstractScreen getScreen() {
		return (AbstractScreen) GravityPangolinGame.getInstance().getScreen();
	}

	public static float projectCoordinateX(float x) {
		return x * getScreen().getPpuX();
	}

	public static float projectCoordinateY(float y) {
		return y * getScreen().getPpuY();
	}

	public static boolean isOutOfScreen(Rectangle rectangle) {
		// AbstractScreen screen = GameUtil.getScreen();
		//
		// final float DEEP_SPACE_MARGIN = screen.getWidth() / 20;
		//
		// float x = rectangle.getX();
		// float y = rectangle.getY();
		// float width = rectangle.getWidth();
		// float height = rectangle.getHeight();
		//
		// if (x < -width - DEEP_SPACE_MARGIN || x > screen.getWidth() +
		// DEEP_SPACE_MARGIN)
		// return true;
		// if (y < -height - DEEP_SPACE_MARGIN || y > screen.getHeight() +
		// DEEP_SPACE_MARGIN)
		// return true;

		return false;
	}

	public static float directionToAngle(Direction direction) {
		switch (direction) {
		case DOWN:
			return 0;
		case LEFT:
			return 90;
		case UP:
			return 180;
		case RIGHT:
			return 270;
		}
		return 0;
	}
}
