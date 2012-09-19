package fr.gravity.pangolin.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.gravity.pangolin.screen.AbstractScreen;
import fr.gravity.pangolin.util.GameUtil;

public abstract class EntityGraphic extends Sprite {

	protected float stateTime = 0;

	/**
	 * Sets the sprite with position
	 * 
	 * @param sprite
	 * @param x
	 * @param y
	 */
	public void set(Sprite sprite, float x, float y) {
		set(sprite);
		setPosition(x, y);
	}

	@Override
	public void set(Sprite sprite) {
		// Get a copy of the x and y values to reset it after setting the sprite
		// (setting a sprite resets the coordinates)
		float xCpy = getX();
		float yCpy = getY();

		// Set the sprite and its size
		super.set(sprite);
		AbstractScreen screen = GameUtil.getScreen();
		setSize(Math.abs(sprite.getWidth()) / screen.getPpuX(),
				Math.abs(sprite.getHeight()) / screen.getPpuY());

		// Reset the coordinates
		setX(xCpy);
		setY(yCpy);
	}

	@Override
	public void setPosition(float x, float y) {
		setX(GameUtil.projectCoordinateX(x));
		setY(GameUtil.projectCoordinateY(y));
	}

	public void resetStateTime() {
		stateTime = 0;
	}

	public void addStateTime(float add) {
		stateTime += add;
	}

	public abstract void touchDown();

	public abstract void touchDownOut();
	
	public abstract void touchUp();
	
	public abstract void touchUpOut();
}
