package fr.gravity.pangolin.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.util.SpriteUtil;

public abstract class Entity extends Actor {

	protected EntityGraphic entityGraphic;

	public Rectangle getBoundingRectangle() {
//		Rectangle r = new Rectangle(entityGraphic.getX(), entityGraphic.getY(), entityGraphic.getWidth(), entityGraphic.getHeight());
//		return r;
		return entityGraphic.getBoundingRectangle();
	}

	public float getX() {
		return entityGraphic.getX();
	}

	public void setX(float x) {
		this.x = x;
		entityGraphic.setX(x);
	}

	public float getY() {
		return entityGraphic.getY();
	}

	public void setY(float y) {
		this.y = y;
		entityGraphic.setY(y);
	}

	public float getWidth() {
		return entityGraphic.getWidth();
	}
	
	public float getHeight() {
		return entityGraphic.getHeight();
	}
	
	public Vector2 getPosition() {
		return new Vector2(getX(), getY());
	}

	public void setPosition(Vector2 position) {
		setX(position.x);
		setY(position.y);
	}

	public EntityGraphic getEntityGraphic() {
		return entityGraphic;
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float alphaModulation) {
		entityGraphic.draw(spriteBatch, alphaModulation);
	}
	
	public abstract Entity collides();
//	public abstract void draw(SpriteBatch spriteBatch);

	/* TOUCH EVENTS */

	protected boolean touchedDown;

	public void touchDown(float x, float y) {
		if (SpriteUtil.isTouched(entityGraphic, x, y)) {
			touchDown();
			entityGraphic.touchDown();
			touchedDown = true;
		} else {
			touchedDown = false;
			entityGraphic.touchDownOut();
		}
	}

	public void touchUp(float x, float y) {
		if (touchedDown && SpriteUtil.isTouched(entityGraphic, x, y)) {
			touchUp();
			entityGraphic.touchUp();
		} else
			entityGraphic.touchUpOut();
	}

	public abstract void touchDown();

	public abstract void touchUp();
}
