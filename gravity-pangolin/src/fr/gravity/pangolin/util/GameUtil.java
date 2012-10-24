package fr.gravity.pangolin.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Rectangle;

import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.screen.AbstractScreen;
import fr.gravity.pangolin.screen.IScreen;

public class GameUtil {

	public static IScreen getScreen() {
		Screen screen = GravityPangolinGame.getInstance().getScreen();
		if (screen instanceof IScreen)
			return (IScreen) screen;
		return null;
	}

	public static float projectCoordinateX(float x) {
		return x * (1 / ((AbstractScreen) getScreen()).getPpuX());
	}

	public static float projectCoordinateY(float y) {
		AbstractScreen screen = (AbstractScreen) getScreen();
		return screen.getHeight() - (y * (1 / screen.getPpuY()));
	}

	private final static float DEEP_SPACE_MARGIN = 3;
	public static boolean isOutOfScreen(float x, float y) {
		if (x < -DEEP_SPACE_MARGIN || y < -DEEP_SPACE_MARGIN)
			return true;
		return false;
	}

	public static boolean isOppositeDirection(Direction direction1, Direction direction2) {
		Direction[] directionValues = Direction.values();
		return directionValues[(direction1.ordinal() + 2) % directionValues.length] == direction2;
	}
}
