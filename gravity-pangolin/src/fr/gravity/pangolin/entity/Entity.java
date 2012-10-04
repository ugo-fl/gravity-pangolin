package fr.gravity.pangolin.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import fr.gravity.pangolin.entity.graphic.EntityGraphic;
import fr.gravity.pangolin.util.SpriteUtil;

public abstract class Entity extends Group {

	protected EntityGraphic entityGraphic;

	public Rectangle getBoundingRectangle() {
		return entityGraphic.getBoundingRectangle();
	}

	public float getX() {
		return entityGraphic.x;
	}

	public void setX(float x) {
		entityGraphic.x = x;
	}

	public float getY() {
		return entityGraphic.y;
	}

	public void setY(float y) {
		entityGraphic.y = y;
	}

	public float getWidth() {
		return entityGraphic.width;
	}
	
	public float getHeight() {
		return entityGraphic.height;
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

	public abstract Entity collides();

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
