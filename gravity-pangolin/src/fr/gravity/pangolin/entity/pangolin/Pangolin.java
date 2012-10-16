package fr.gravity.pangolin.entity.pangolin;

import java.util.ArrayList;
import java.util.List;

import test.BodyEditorLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJoint;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.block.GravityChangerBlock;
import fr.gravity.pangolin.entity.graphic.pangolin.PangolinGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.game.Controller;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.util.CountDown;
import fr.gravity.pangolin.util.GameUtil;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class Pangolin extends Entity {

	public static final float SPEED = 0.8F; // unit per second
	public static final float FALLING_SPEED = SPEED / 1.8F; // unit per second

	public static final float MAX_VELOCITY = 8;

	private Controller controller;

	/* DIRECTION */

	public enum Direction {
		LEFT(270), UP(180), RIGHT(90), DOWN(0);

		// The corresponding angle
		float angle;

		private Direction(float angle) {
			this.angle = angle;
		}
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

	private PangolinState pangolinState;

	/* POSITION */

	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	private boolean controllerEnabled = true;

	private Fixture feetSensorFixture;

	public Pangolin(World world, float x, float y) {
		super(world, x, y, 2);
		createFeetSensor();
	}

	@Override
	protected void createBody(World world, float x, float y) {
		BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("data/body.json"));

		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = BodyType.DynamicBody;

		FixtureDef bodyFixtureDef = new FixtureDef();
		bodyFixtureDef.density = 1;
		bodyFixtureDef.friction = 0;
		bodyFixtureDef.restitution = 0;

		body = world.createBody(bd);

		loader.attachFixture(body, "pangolin", bodyFixtureDef, scale);
		origin = loader.getOrigin("pangolin", scale).add(body.getLocalCenter()).cpy();

		body.setFixedRotation(true);
		body.setUserData(this);
		
		body.setTransform(body.getPosition().add(origin), body.getAngle());
	}

	private RevoluteJoint rj;

	// The fixtures that come in contact with the feet
	private List<Entity> inContactEntities = new ArrayList<Entity>();

	/**
	 * Initiate the feet sensor
	 */
	private void createFeetSensor() {
		PolygonShape polygonShape = new PolygonShape();
		FixtureDef myFixtureDef = new FixtureDef();
		myFixtureDef.shape = polygonShape;
		myFixtureDef.density = 1;

		polygonShape.setAsBox(0.8f, 0.2f, body.getLocalCenter().add(new Vector2(-0.1F, -0.3F)), 0);
		myFixtureDef.isSensor = true;
		feetSensorFixture = body.createFixture(myFixtureDef);
		feetSensorFixture.setUserData(this);
	}

	@Override
	public void createGraphic(float x, float y) {
		entityGraphic = new PangolinGraphic(this, x, y);
		controller = new Controller(GravityPangolinWorld.getInstance(), this);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (controllerEnabled)
			controller.update(delta);
		clear();
		((PangolinGraphic) entityGraphic).updateFrame();

		Direction gravityDirection = GravityPangolinWorld.getInstance().getGravity().direction;
		if ((int) gravityDirection.angle != (int) Math.toDegrees(body.getAngle())) {
			rotate(gravityDirection);
		}
	}

	public void idle() {
		((PangolinGraphic) entityGraphic).idle();
		pangolinState = PangolinState.IDLE;
		getAcceleration().x = 0;
		getVelocity().x = 0;
		getVelocity().y = 0;
		body.setLinearVelocity(new Vector2());
	}

	public void go(Direction direction) {
		((PangolinGraphic) entityGraphic).move();
		pangolinState = PangolinState.WALKING;
		this.direction = direction;
		move(direction, false);
	}

	private final static float FALLING_IMPULSE = 1;

	public void fall(Direction direction) {
		pangolinState = PangolinState.FALLING;

		float forceX = 0;
		float forceY = 0;
		switch (direction) {
		case DOWN:
			forceY = -FALLING_IMPULSE;
		case UP:
			forceY = FALLING_IMPULSE;
		case LEFT:
			forceX = -FALLING_IMPULSE;
		case RIGHT:
			forceX = FALLING_IMPULSE;
		}
		body.applyForce(new Vector2(forceX, forceY), body.getPosition().add(body.getLocalCenter()));
	}

	private void move(Direction direction, boolean falling) {
		Vector2 movement = null;
		Vector2 velocity = body.getLinearVelocity();
		float speed = (pangolinState == PangolinState.FALLING ? FALLING_SPEED : SPEED);
		if (direction == Direction.LEFT || direction == Direction.RIGHT) {
			speed *= (Math.abs(MAX_VELOCITY) - Math.abs(velocity.x));
			movement = new Vector2(direction == Direction.LEFT ? -speed : speed, 0);
		} else if (direction == Direction.UP || direction == Direction.DOWN) {
			speed *= (Math.abs(MAX_VELOCITY) - Math.abs(velocity.y));
			movement = new Vector2(0, direction == Direction.DOWN ? -speed : speed);
		}
		body.applyLinearImpulse(movement, body.getWorldCenter());
	}

	@Override
	public void beginContact(Object entity) {
		if (entity instanceof GravityChangerBlock)
			return;
		inContactEntities.add((Entity) entity);
	}

	@Override
	public void endContact(Object entity) {
		inContactEntities.remove((Entity) entity);
	}

	/** EVENTS **/

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
		return inContactEntities.size() > 0;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void rotate(Direction direction) {
		System.out.println("ROTATE " + direction.angle);
		body.setTransform(body.getPosition(), (float) Math.toRadians(direction.angle));
	}

	public Direction getDirection() {
		return direction;
	}

}
