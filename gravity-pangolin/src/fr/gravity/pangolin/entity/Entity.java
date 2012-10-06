package fr.gravity.pangolin.entity;

import test.BodyEditorLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.scenes.scene2d.Group;

import fr.gravity.pangolin.entity.graphic.EntityGraphic;
import fr.gravity.pangolin.screen.TestBox2DScreen;
import fr.gravity.pangolin.util.SpriteUtil;

public abstract class Entity extends Group {

	// The graphic part of the entity
	protected EntityGraphic entityGraphic;
	
	// The body corresponding to the physics of the entity
	protected Body body;
	
	// The scale for the object (default is 1 unit)
	protected float scale = 1;
	
	// The origin to draw the sprite correctly
	protected Vector2 origin;
	
	public Entity(World world, float x, float y) {
		createGraphic(x, y);
		createBody(world, x, y);
		if (body == null)
			throw new NullPointerException("The " + getName() + "'s body cannot be null.");
		if (origin == null)
			throw new NullPointerException("The " + getName() + "'s origin cannot be null.");
	}
	
	public abstract void createGraphic(float x, float y);
	
	private void createBody(World world, float x, float y) {
	    // 0. Create a loader for the file saved from the editor.
	    BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("data/body.json"));
	 
	    // 1. Create a BodyDef, as usual.
	    BodyDef bd = new BodyDef();
	    bd.position.set(0, 0);
	    bd.type = BodyType.DynamicBody;
	 
	    // 2. Create a FixtureDef, as usual.
	    FixtureDef fd = new FixtureDef();
	    fd.density = 1;
	    fd.friction = 0.5f;
	    fd.restitution = 0.3f;
	 
	    // 3. Create a Body, as usual.
	    body = world.createBody(bd);
	    
	    // 4. Create the body fixture automatically by using the loader.
	    loader.attachFixture(body, "wall_block", fd, scale);
	    
	    origin = loader.getOrigin("wall_block", scale).cpy();
	}
	
	protected abstract String getBodyName();
	
	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
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

	public EntityGraphic getEntityGraphic() {
		return entityGraphic;
	}

	private String getName() {
		return getClass().getSimpleName();
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
