package fr.gravity.pangolin.entity.graphic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.util.GameUtil;

public abstract class EntityGraphic extends Sprite {

	protected float stateTime = 0;
	
	public EntityGraphic() {
		// TODO Auto-generated constructor stub
	}
	
	public EntityGraphic(TextureRegion region, float x, float y) {
		super(region);
		setPosition(x, y);
//		width = GameUtil.getScreen().getPpuX();
//		height = GameUtil.getScreen().getPpuY();
	}
	

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

	@Override
	public void set(Sprite sprite) {

		// Get a copy of the x and y values to reset it after setting the sprite
		// (setting a sprite resets the coordinates)
		float xCpy = getX();
		float yCpy = getY();

		// Set the sprite and its size
		super.set(sprite);

		// Reset the coordinates
		setX(xCpy);
		setY(yCpy);
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
