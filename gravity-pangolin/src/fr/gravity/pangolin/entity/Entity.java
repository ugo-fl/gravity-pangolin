package fr.gravity.pangolin.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	protected EntityGraphic entityGraphic;
	
	public Rectangle getBoundingRectangle() {
		return entityGraphic.getBoundingRectangle();
	}
	
	public float getX() {
		return entityGraphic.getX();
	}

	public void setX(float x) {
		entityGraphic.setX(x);
	}
	
	public float getY() {
		return entityGraphic.getY();
	}
	
	public void setY(float y) {
		entityGraphic.setY(y);
	}
	
	public Vector2 getPosition() {
		return new Vector2(getX(), getY());
	}
	
	public void setPosition(Vector2 position) {
		setX(position.x);
		setY(position.y);
	}
	
	public abstract boolean collides();
	public abstract void draw(SpriteBatch spriteBatch);
}
