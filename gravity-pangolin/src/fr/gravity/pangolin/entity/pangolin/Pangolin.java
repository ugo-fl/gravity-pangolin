package fr.gravity.pangolin.entity.pangolin;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.gravity.pangolin.entity.Entity;

public class Pangolin extends Entity {

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

	// State
	private enum PangolinState {
		IDLE, WALKING, FALLING;
		
		private PangolinGraphic pangolinGraphic;
		
		public void setPangolinGraphic(PangolinGraphic graphic) {
			this.pangolinGraphic = graphic;
		}

		public PangolinGraphic getPangolinGraphic() {
			return pangolinGraphic;
		}

		public void setPosition(float x, float y) {
			pangolinGraphic.setX(x);
			pangolinGraphic.setY(y);
		}
	}
	
	private PangolinState pangolinState = PangolinState.IDLE;
	
//	private IdlePangolinGraphic idlePangolinState;
//	private WalkingPangolinGraphic walkingPangolinState;
//	private FallingPangolinGraphic fallingPangolinState;

//	private Vector2 position = new Vector2();
	private Vector2 previousPosition = new Vector2();
	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	private Rectangle bounds = new Rectangle();
	private boolean landed;

	public Pangolin(float x, float y) {
//		idlePangolinState = new IdlePangolinGraphic(this, x, y);
//		walkingPangolinState = new WalkingPangolinGraphic(this, x, y);
//		fallingPangolinState = new FallingPangolinGraphic(this, x, y);
		
		PangolinState.IDLE.setPangolinGraphic(new IdlePangolinGraphic(this, x, y));
		PangolinState.WALKING.setPangolinGraphic(new WalkingPangolinGraphic(this, x, y));
		PangolinState.FALLING.setPangolinGraphic(new FallingPangolinGraphic(this, x, y));
		
		entityGraphic = pangolinState.getPangolinGraphic();
	}
	
//	public Pangolin(Vector2 position) {
//		this.position = position;
//		this.bounds.width = WIDTH;
//		this.bounds.height = HEIGHT;
//		pangolinState = idlePangolinState;
//	}

	public void idle() {
		pangolinState = PangolinState.IDLE;
		getAcceleration().x = 0;
		getVelocity().x = 0;
		getVelocity().y = 0;
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
			pangolinState.setPosition(getX() + translation.x, getY() - translation.y);
		}
//		pangolinState.setPosition(getX() + translation.x, getY() - translation.y);
//		System.out.println("TRANSLATION (" + translation.x + ", " + translation.y + ")"); 
	}

	public void goLeft() {
		pangolinState = PangolinState.WALKING;
		direction = Direction.LEFT;
//		setEntityGraphic(walkingPangolinState);
		velocity.x = -SPEED;
	}

	public void fallLeft() {
		if (!landed)
			pangolinState = PangolinState.FALLING;
//			setEntityGraphic(fallingPangolinState);
		velocity.x = -FALLING_SPEED;
	}

	public void goRight() {
		pangolinState = PangolinState.WALKING;
		direction = Direction.RIGHT;
//		setEntityGraphic(walkingPangolinState);
		velocity.x = SPEED;
	}

	public void fallRight() {
		if (!landed)
			pangolinState = PangolinState.FALLING;
//			setEntityGraphic(fallingPangolinState);
		velocity.x = FALLING_SPEED;
	}

	public void goUp() {
		pangolinState = PangolinState.WALKING;
		direction = Direction.UP;
//		setEntityGraphic(walkingPangolinState);
		velocity.y = -SPEED;
	}

	public void fallUp() {
		if (!landed)
			pangolinState = PangolinState.FALLING;
//			setEntityGraphic(fallingPangolinState);
		velocity.y = -FALLING_SPEED;
	}

	public void goDown() {
		pangolinState = PangolinState.WALKING;
		direction = Direction.DOWN;
//		setEntityGraphic(walkingPangolinState);
		velocity.y = SPEED;
	}

	public void fallDown() {
		if (!landed)
			pangolinState = PangolinState.FALLING;
//			setEntityGraphic(fallingPangolinState);
		velocity.y = FALLING_SPEED;
	}

	public void rollBackPosition() {
		setX(previousPosition.x);
		setY(previousPosition.y);
	}
	
	@Override
	public void draw(SpriteBatch spriteBatch) {
		pangolinState.getPangolinGraphic().draw(spriteBatch);
	}

	@Override
	public boolean collides() {
		return false;
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
