package test;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class TextureWrapper {
	private TextureRegion region;
	private int width;
	private int height;
	private Vector2 position;
	private float scaleX;
	private float scaleY;
	private float originX;
	private float originY;
	private float rotation;

	public TextureWrapper(TextureRegion region, Vector2 pos) {
		this.position = pos;
		setTextureRegion(region);
	}

	public void setTextureRegion(TextureRegion region) {
		this.region = region;
		width = region.getRegionWidth();
		height = region.getRegionHeight();
		originX = width / 2;
		originY = height / 2;
		scaleX = 1;
		scaleY = 1;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setPosition(float x, float y) {
		position.set(x, y);
	}

	public void setRotation(float r) {
		rotation = r;
	}

	public void draw(SpriteBatch sp) {
//		sp.draw(region, position.x - 1, position.y - 1, // the bottom left corner of the box, unrotated
//				1f, 1f, // the rotation center relative to the bottom left corner of the box
//				2, 2, // the width and height of the box
//				1, 1, // the scale on the x- and y-axis
//				angle);
//		sp.draw(region, position.x - width / 2, position.y - height / 2, originX, originY, width, height, scaleX, scaleY, rotation);
	}
}
