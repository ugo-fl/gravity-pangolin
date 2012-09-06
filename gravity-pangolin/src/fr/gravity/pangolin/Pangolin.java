package fr.gravity.pangolin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pangolin {

	public enum State {
		IDLE, WALKING_RIGHT, WALKING_LEFT, FALLING, DYING
	}

	public static final float SPEED = 4f; // unit per second
	public static final float JUMP_VELOCITY = 4f;
	
	private float delta;

	/* Movement and position */
	private Vector2 position = new Vector2();
	private Vector2 previousPosition = new Vector2();
	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	private Rectangle bounds = new Rectangle();
	private State state = State.IDLE;
	private boolean landed;

	/* Sprites */
	public static final float WIDTH = 1.8f; // half a unit
	public static final float HEIGHT = 1f; // half a unit
	
	private static final int FRAME_COLS = 2;
	private static final int FRAME_ROWS = 1;

	private Animation walkAnimationRight;
	private Animation walkAnimationLeft;
	private Animation currentWalkAnimation;
	private float stateTime = 0f;

	public Pangolin(Vector2 position) {
		this.position = position;
		this.bounds.width = WIDTH;
		this.bounds.height = HEIGHT;
		loadTextures();
	}

	private void loadTextures() {
		Texture pangolinTexture = new Texture(
				Gdx.files.internal("images/sprite_pangolin.png"));
		TextureRegion[][] tmp = TextureRegion.split(pangolinTexture,
				pangolinTexture.getWidth() / FRAME_COLS,
				pangolinTexture.getHeight() / FRAME_ROWS);
		TextureRegion[][] tmp2 = TextureRegion.split(pangolinTexture,
				pangolinTexture.getWidth() / FRAME_COLS,
				pangolinTexture.getHeight() / FRAME_ROWS);
		TextureRegion[] walkFramesRight = new TextureRegion[FRAME_COLS
				* FRAME_ROWS];
		TextureRegion[] walkFramesLeft = new TextureRegion[FRAME_COLS
				* FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				tmp[i][j].flip(false, true);
				walkFramesRight[index] = tmp[i][j];
				tmp2[i][j].flip(true, true);
				walkFramesLeft[index] = tmp2[i][j];
				index++;
			}
		}
		walkAnimationRight = new Animation(0.25f, walkFramesRight);
		walkAnimationLeft = new Animation(0.25f, walkFramesLeft);
		currentWalkAnimation = walkAnimationRight;
	}

	public void idle() {
		setState(State.IDLE);
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
		state = State.WALKING_LEFT;
		currentWalkAnimation = walkAnimationLeft;
		velocity.x = -Pangolin.SPEED;
	}

	public void fallLeft() {
		if (!landed)
			state = State.FALLING;
		velocity.x = -Pangolin.SPEED;
	}

	public void goRight() {
		state = State.WALKING_RIGHT;
		currentWalkAnimation = walkAnimationRight;
		velocity.x = Pangolin.SPEED;
	}

	public void fallRight() {
		if (!landed)
			state = State.FALLING;
		velocity.x = Pangolin.SPEED;
	}

	public void goUp() {
		state = State.WALKING_RIGHT;
		velocity.y = -Pangolin.SPEED;
	}

	public void fallUp() {
		if (!landed)
			state = State.FALLING;
		velocity.y = -Pangolin.SPEED;
	}

	public void goDown() {
		state = State.WALKING_LEFT;
		velocity.y = Pangolin.SPEED;
	}

	public void fallDown() {
		if (!landed)
			state = State.FALLING;
		velocity.y = Pangolin.SPEED;
	}

	public void rollBackPosition() {
		position = previousPosition;
	}

	public void flipWalkAnimations() {
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
			}
		}
	}
	
	/** GETTERS & SETTERS **/

	public TextureRegion getCurrentFrame() {
		TextureRegion frame;
		if (state == State.IDLE)
			stateTime = 0;
		else
			stateTime += Gdx.graphics.getDeltaTime();
		frame = currentWalkAnimation.getKeyFrame(stateTime, true);
		return frame;
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

	public State getState() {
		return state;
	}

	public void setState(State newState) {
		this.state = newState;
	}

	public boolean isLanded() {
		return landed;
	}

	public void setLanded(boolean landed) {
		this.landed = landed;
	}

}
