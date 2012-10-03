package fr.gravity.pangolin.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import fr.gravity.pangolin.util.GameUtil;

public abstract class EntityGraphic extends Image {

	protected float stateTime = 0;

	/**
	 * Sets the sprite with position
	 * 
	 * @param sprite
	 * @param x
	 * @param y
	 */
	public void set(TextureRegion region, float x, float y) {
		setRegion(region);
//		set(sprite);
		setPosition(x, y);
	}

	public Rectangle getBoundingRectangle() {
		return new Rectangle(x, y, width, height);
	}
	
//	@Override
//	public void set(Sprite sprite) {
/*
		// Get a copy of the x and y values to reset it after setting the sprite
		// (setting a sprite resets the coordinates)
		float xCpy = getX();
		float yCpy = getY();
*/
		// Set the sprite and its size
//		super.set(sprite);
//		AbstractScreen2 screen = GameUtil.getScreen();
//		setSize(Math.abs(sprite.getWidth()) / screen.getPpuX(),
//				Math.abs(sprite.getHeight()) / screen.getPpuY());

		/*
		// Reset the coordinates
		setX(xCpy);
		setY(yCpy);
		*/
//	}

//	@Override
	public void setPosition(float x, float y) {
		this.x = GameUtil.projectCoordinateX(x);
		this.y = GameUtil.projectCoordinateY(y);
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
