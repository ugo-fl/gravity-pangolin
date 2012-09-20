package fr.gravity.pangolin.entity.pangolin;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.Gravity.Side;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.DyingPangolinGraphic;
import fr.gravity.pangolin.entity.graphic.FallingPangolinGraphic;
import fr.gravity.pangolin.entity.graphic.IdlePangolinGraphic;
import fr.gravity.pangolin.entity.graphic.PangolinGraphic;
import fr.gravity.pangolin.entity.graphic.WalkingPangolinGraphic;

public class Pangolin extends Entity {

	public static final float SPEED = 4f; // unit per second
	public static final float FALLING_SPEED = 7f; // unit per second
	public static final float JUMP_VELOCITY = 4f;

	private float delta;

	/* DIRECTION */

	public enum Direction {
		LEFT, RIGHT, UP, DOWN
	}

	private Direction direction = Direction.RIGHT;

	/* STATE */

	private enum PangolinState {
		IDLE, WALKING, FALLING, DYING;

		private PangolinGraphic pangolinGraphic;

		public void setPangolinGraphic(PangolinGraphic graphic) {
			this.pangolinGraphic = graphic;
		}

		public void setPosition(float x, float y) {
			pangolinGraphic.setX(x);
			pangolinGraphic.setY(y);
		}
	}

	private PangolinState pangolinState = PangolinState.IDLE;

	/* POSITION */

	private Vector2 previousPosition = new Vector2();
	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	private boolean landed;

	public Pangolin(float x, float y) {
		PangolinState.IDLE.setPangolinGraphic(new IdlePangolinGraphic(this, x,
				y));
		PangolinState.WALKING.setPangolinGraphic(new WalkingPangolinGraphic(
				this, x, y));
		PangolinState.FALLING.setPangolinGraphic(new FallingPangolinGraphic(
				this, x, y));
		PangolinState.DYING.setPangolinGraphic(new DyingPangolinGraphic(this,
				x, y));

		entityGraphic = pangolinState.pangolinGraphic;
	}

	public void update(float delta) {
		previousPosition = new Vector2(getX(), getY());
		this.delta = delta;
		translate();
	}

	public void update() {
		previousPosition = new Vector2(getX(), getY());
		translate();
	}

	public void translate() {
		translate(velocity.tmp().mul(delta));
	}

	public void translate(Vector2 translation) {
		for (PangolinState pangolinState : PangolinState.values()) {
			pangolinState.setPosition(getX() + translation.x, getY()
					- translation.y);
		}
	}

	public void idle() {
		pangolinState = PangolinState.IDLE;
		getAcceleration().x = 0;
		getVelocity().x = 0;
		getVelocity().y = 0;
	}

//	public void goLeft() {
//		pangolinState = PangolinState.WALKING;
//		direction = Direction.LEFT;
//		velocity.x = -SPEED;
//	}
//
//	public void goRight() {
//		pangolinState = PangolinState.WALKING;
//		direction = Direction.RIGHT;
//		velocity.x = SPEED;
//	}
//
//	public void goUp() {
//		pangolinState = PangolinState.WALKING;
//		direction = Direction.UP;
//		velocity.y = -SPEED;
//	}
//
//	public void goDown() {
//		pangolinState = PangolinState.WALKING;
//		direction = Direction.DOWN;
//		velocity.y = SPEED;
//	}

	public void go(Direction direction) {
		if (landed)
			pangolinState = PangolinState.WALKING;
		this.direction = direction;
		if (direction == Direction.LEFT || direction == Direction.RIGHT)
			velocity.x = direction == Direction.LEFT ? -SPEED : SPEED;
		else if (direction == Direction.UP || direction == Direction.DOWN)
			velocity.y = direction == Direction.UP ? -SPEED : SPEED;
	}

	public void fall(Side side) {
		switch (side) {
		case UP:
			fallUp();
		case DOWN:
			fallDown();
		case LEFT:
			fallLeft();
		case RIGHT:
			fallRight();
		}
	}

	public void fallUp() {
		if (!landed)
			pangolinState = PangolinState.FALLING;
		velocity.y = -FALLING_SPEED;
	}

	public void fallDown() {
		if (!landed)
			pangolinState = PangolinState.FALLING;
		velocity.y = FALLING_SPEED;
	}

	public void fallLeft() {
		if (!landed)
			pangolinState = PangolinState.FALLING;
		velocity.x = -FALLING_SPEED;
	}

	public void fallRight() {
		if (!landed)
			pangolinState = PangolinState.FALLING;
		velocity.x = FALLING_SPEED;
	}

	public void land(Side gravitySide) {
		landed = true;
		if (gravitySide == Side.RIGHT)
			if (direction == Direction.RIGHT || direction == Direction.UP)
				direction = Direction.UP;
	}

	public void rollBackPosition() {
		setX(previousPosition.x);
		setY(previousPosition.y);
	}

//	@Override
//	public void draw(SpriteBatch spriteBatch) {
//		entityGraphic = pangolinState.pangolinGraphic;
//		entityGraphic.draw(spriteBatch);
//	}

	@Override
	public boolean collides() {
		return false;
	}

	/** TOUCH EVENTS **/

	@Override
	public void touchDown() {
	}

	@Override
	public void touchUp() {
	}

	/** GETTERS & SETTERS **/

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
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

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		entityGraphic = pangolinState.pangolinGraphic;
		entityGraphic.draw(batch);
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}
