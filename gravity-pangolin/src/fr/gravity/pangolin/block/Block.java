package fr.gravity.pangolin.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Block {

	public static float WIDTH = 1F;
	public static float HEIGHT = 1F;

	protected Vector2 position = new Vector2();
	protected Rectangle bounds = new Rectangle();

	public Block(Vector2 pos) {
		this.position = pos;
		this.bounds.width = WIDTH;
		this.bounds.height = HEIGHT;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setSize(float width, float height) {
		bounds.width = width;
		bounds.height = height;
	}

	public Rectangle getTextureBounds() {
		return bounds;
	}
	
	/* This method must return true if the Block is solid (means that the Pangolin will stop its movement on collision) */
	public abstract boolean collides();
	
	public abstract TextureRegion getTextureRegion();
	
}
