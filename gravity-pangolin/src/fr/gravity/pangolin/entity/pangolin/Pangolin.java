package fr.gravity.pangolin.entity.pangolin;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.DyingPangolinGraphic;
import fr.gravity.pangolin.entity.graphic.FallingPangolinGraphic;
import fr.gravity.pangolin.entity.graphic.IdlePangolinGraphic;
import fr.gravity.pangolin.entity.graphic.PangolinGraphic;
import fr.gravity.pangolin.entity.graphic.WalkingPangolinGraphic;
import fr.gravity.pangolin.game.Controller;
import fr.gravity.pangolin.game.GravityPangolinGame;

public class Pangolin extends Entity {

	public static final float SPEED = 150; // unit per second
	public static final float FALLING_SPEED = SPEED * 1.8F; // unit per second

	private Controller controller;
	private float delta;

	/* DIRECTION */

	public enum Direction {
		LEFT, UP, RIGHT, DOWN
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
			pangolinGraphic.x = x;
			pangolinGraphic.y = y;
		}
	}

	private PangolinState pangolinState = PangolinState.IDLE;

	/* POSITION */

	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	private boolean landed = false;
	private boolean controllerEnabled = true;

	public Pangolin() {
	}

	public void init(float x, float y) {
		PangolinState.IDLE.setPangolinGraphic(new IdlePangolinGraphic(this, x, y));
		PangolinState.WALKING.setPangolinGraphic(new WalkingPangolinGraphic(this, x, y));
		PangolinState.FALLING.setPangolinGraphic(new FallingPangolinGraphic(this, x, y));
		PangolinState.DYING.setPangolinGraphic(new DyingPangolinGraphic(this, x, y));

		entityGraphic = pangolinState.pangolinGraphic;
		controller = new Controller(GravityPangolinGame.getInstance().getPangolinWorld(), this);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (controllerEnabled)
			controller.update(delta);
		update(delta);
		entityGraphic = pangolinState.pangolinGraphic;
	}

	public void update(float delta) {
		this.delta = delta;
		translate();
	}

	public void update() {
		translate();
	}

	public void translate() {
		translate(velocity.tmp().mul(delta));
	}

	public void translate(Vector2 translation) {
		for (PangolinState pangolinState : PangolinState.values()) {
			pangolinState.setPosition(getX() + translation.x, getY() - translation.y);
		}
	}

	public void idle() {
		pangolinState = PangolinState.IDLE;
		getAcceleration().x = 0;
		getVelocity().x = 0;
		getVelocity().y = 0;
	}

	public void go(Direction direction) {
		if (landed)
			pangolinState = PangolinState.WALKING;
		this.direction = direction;
		move(direction, false);
	}

	public void fall(Direction direction) {
		if (!landed)
			pangolinState = PangolinState.FALLING;
		move(direction, true);
	}

	private void move(Direction direction, boolean falling) {
		float speed = (falling ? FALLING_SPEED : SPEED);

		if (direction == Direction.LEFT || direction == Direction.RIGHT)
			velocity.x = direction == Direction.LEFT ? -speed : speed;
		else if (direction == Direction.UP || direction == Direction.DOWN)
			velocity.y = direction == Direction.UP ? -speed : speed;
	}

	public void disableController() {
		controllerEnabled = true;
	}

	/** EVENTS **/

	@Override
	public Entity collides() {
		return null;
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}

	@Override
	public boolean keyDown(int keycode) {
		controller.keyDown(keycode);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		controller.keyUp(keycode);
		return true;
	}

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

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

}
