package fr.gravity.pangolin.entity.graphic;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import fr.gravity.pangolin.util.GameUtil;

public abstract class EntityGraphic extends Image {

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

	public Rectangle getBoundingRectangle() {
		return new Rectangle(x, y, width, height);
	}
	
	public BoundingBox getBoundingBox() {
		BoundingBox boundingBox = new BoundingBox();
		Vector3 minimum = new Vector3(x, y, 0);
		Vector3 maximum = new Vector3(x + width, y + height, 0);
		boundingBox.set(minimum, maximum);
		return boundingBox;
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
