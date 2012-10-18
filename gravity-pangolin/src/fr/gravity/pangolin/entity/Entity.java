package fr.gravity.pangolin.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;

import fr.gravity.pangolin.entity.graphic.EntityGraphic;
import fr.gravity.pangolin.util.SpriteUtil;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public abstract class Entity extends Group {

	protected GravityPangolinWorld gravityPangolinWorld;
	
	// The graphic part of the entity
	protected EntityGraphic entityGraphic;
	
	// The body corresponding to the physics of the entity
	protected Body body;
	
	// The scale for the object (default is 1 unit)
	protected float scale = 1;
	
	// The origin to draw the sprite correctly
	protected Vector2 origin;
	
	public Entity(GravityPangolinWorld gravityPangolinWorld, float scale) {
		this.gravityPangolinWorld = gravityPangolinWorld;
		this.scale = scale;
		
//		if (body == null)
//			throw new NullPointerException("The " + getName() + "'s body cannot be null.");
//		if (origin == null)
//			throw new NullPointerException("The " + getName() + "'s origin cannot be null.");
	}
	
//	public void init(float x, float y) {
//		createGraphic(x, y);
//		createBody(gravityPangolinWorld.getWorld(), x, y);
//	}
	
//	public abstract void createGraphic(float x, float y);
//	
//	protected abstract void createBody(World world, float x, float y);
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		if (entityGraphic == null)
			return ;
		
		Vector2 bodyPos = body.getPosition().sub(origin);

		entityGraphic.setPosition(bodyPos.x, bodyPos.y);
		entityGraphic.setOrigin(origin.x, origin.y);
		entityGraphic.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
		entityGraphic.setSize(scale, scale * entityGraphic.getHeight() / entityGraphic.getWidth());

		entityGraphic.draw(batch);
	}
	
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

	public Body getBody() {
		return body;
	}

	public EntityGraphic getEntityGraphic() {
		return entityGraphic;
	}

	public abstract void beginContact(Object entity);
	public abstract void endContact(Object entity);
	
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
