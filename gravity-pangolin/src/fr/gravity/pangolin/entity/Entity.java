package fr.gravity.pangolin.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
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
		return body.getPosition().x;
	}

	public void setX(float x) {
		body.setTransform(x, body.getPosition().y, body.getAngle());
	}

	public float getY() {
		return body.getPosition().y;
	}

	public void setY(float y) {
		body.setTransform(body.getPosition().x, y, body.getAngle());
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

	public abstract void beginContact(Object entity, Fixture fixture);
	public abstract void endContact(Object entity, Fixture fixture);
	
	/* TOUCH EVENTS */

	protected boolean touchedDown;

	public boolean touchDown(float x, float y) {
		if (SpriteUtil.isTouched(entityGraphic, x, y)) {
			System.out.println("TOUCHED DOWN " + this);
			touchDown();
			entityGraphic.touchDown();
			touchedDown = true;
			return true;
		} else {
			touchedDown = false;
			entityGraphic.touchDownOut();
		}
		return false;
	}

	public boolean touchUp(float x, float y) {
		if (touchedDown && SpriteUtil.isTouched(entityGraphic, x, y)) {
			touchUp();
			entityGraphic.touchUp();
			return true;
		} else
			entityGraphic.touchUpOut();
		return false;
	}

	public abstract void touchDown();

	public abstract void touchUp();
}
