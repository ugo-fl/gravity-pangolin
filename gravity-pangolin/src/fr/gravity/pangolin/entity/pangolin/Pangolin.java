package fr.gravity.pangolin.entity.pangolin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.entity.Entity;

public class Pangolin extends Entity {

	// public enum State {
	// IDLE, WALKING_RIGHT, WALKING_LEFT, FALLING, DYING
	// }

	public static final float SPEED = 4f; // unit per second
	public static final float FALLING_SPEED = 7f; // unit per second
	public static final float JUMP_VELOCITY = 4f;

	private float delta;

	/* MOVEMENT AND POSITION */

	// Direction
	public enum Direction {
		LEFT, RIGHT, UP, DOWN
	}

	private Direction direction = Direction.RIGHT;

	// States
	private IdlePangolinSprite idlePangolinState;
	private WalkingPangolinSprite walkingPangolinState;
	private FallingPangolinSprite fallingPangolinState;

	private Vector2 position = new Vector2();
	private Vector2 previousPosition = new Vector2();
	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	private Rectangle bounds = new Rectangle();
	// private State state = State.IDLE;
	private boolean landed;

	/* Sprites */
	public static final float WIDTH = 1.8f;
	public static final float HEIGHT = 1f;

	private float stateTime = 0f;

	public Pangolin(float x, float y) {
		entityGraphic = idlePangolinState;
		idlePangolinState = new IdlePangolinSprite(this);
		walkingPangolinState = new WalkingPangolinSprite(this);
		fallingPangolinState = new FallingPangolinSprite(this);
	}
	
//	public Pangolin(Vector2 position) {
//		this.position = position;
//		this.bounds.width = WIDTH;
//		this.bounds.height = HEIGHT;
//		pangolinState = idlePangolinState;
//	}

	public void idle() {
		entityGraphic = idlePangolinState;
		getAcceleration().x = 0;
		getVelocity().x = 0;
		getVelocity().y = 0;
	}

	public void update(float delta) {
		previousPosition = position.cpy();
		this.delta = delta;
		position.add(velocity.tmp().mul(delta));
	}

	public void update() {
		previousPosition = position.cpy();
		position.add(velocity.tmp().mul(delta));
	}

	public void goLeft() {
		direction = Direction.LEFT;
		entityGraphic = walkingPangolinState;
		velocity.x = -SPEED;
	}

	public void fallLeft() {
		if (!landed)
			entityGraphic = fallingPangolinState;
		velocity.x = -FALLING_SPEED;
	}

	public void goRight() {
		direction = Direction.RIGHT;
		entityGraphic = walkingPangolinState;
		velocity.x = SPEED;
	}

	public void fallRight() {
		if (!landed)
			entityGraphic = fallingPangolinState;
		velocity.x = FALLING_SPEED;
	}

	public void goUp() {
		direction = Direction.UP;
		entityGraphic = walkingPangolinState;
		velocity.y = -SPEED;
	}

	public void fallUp() {
		if (!landed)
			entityGraphic = fallingPangolinState;
		velocity.y = -FALLING_SPEED;
	}

	public void goDown() {
		direction = Direction.DOWN;
		entityGraphic = walkingPangolinState;
		velocity.y = SPEED;
	}

	public void fallDown() {
		if (!landed)
			entityGraphic = fallingPangolinState;
		velocity.y = FALLING_SPEED;
	}

	public void rollBackPosition() {
		position = previousPosition;
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		if (entityGraphic instanceof IdlePangolinSprite)
			stateTime = 0;
		else
			stateTime += Gdx.graphics.getDeltaTime();

		entityGraphic.draw(spriteBatch);
	}

	@Override
	public boolean collides() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/** GETTERS & SETTERS **/

	public TextureRegion getCurrentFrame() {
		if (entityGraphic instanceof IdlePangolinSprite)
			stateTime = 0;
		else
			stateTime += Gdx.graphics.getDeltaTime();

		if (entityGraphic instanceof FallingPangolinSprite) {
			bounds.setWidth(FallingPangolinSprite.WIDTH);
			bounds.setHeight(FallingPangolinSprite.HEIGHT);
		} else if (bounds.getWidth() == FallingPangolinSprite.WIDTH) {
			bounds.setWidth(WIDTH);
			bounds.setHeight(HEIGHT);
		}

		return ((PangolinGraphic) entityGraphic).getFrame(stateTime);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public boolean isLanded() {
		return landed;
	}

	public void setLanded(boolean landed) {
		this.landed = landed;
	}

	public Direction getDirection() {
		return direction;
	}

}
