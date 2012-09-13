package fr.gravity.pangolin.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {

	protected EntityGraphic entityGraphic;
	
	public Rectangle getBoundingRectangle() {
		return entityGraphic.getBoundingRectangle();
	}
	
	public float getX() {
		return entityGraphic.getX();
	}
	
	public float getY() {
		return entityGraphic.getY();
	}
	
	public abstract boolean collides();
	public abstract void draw(SpriteBatch spriteBatch);
}
