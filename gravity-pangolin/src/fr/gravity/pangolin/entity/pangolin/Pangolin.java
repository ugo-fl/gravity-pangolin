package fr.gravity.pangolin.entity.pangolin;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import fr.gravity.pangolin.collision.BodyEditorLoader;
import fr.gravity.pangolin.constant.FilterMask;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.pangolin.PangolinGraphic;
import fr.gravity.pangolin.game.Controller;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.util.GameUtil;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class Pangolin extends Entity implements InputProcessor {

	public static final float SPEED = 0.8F; // unit per second
	public static final float FALLING_SPEED = SPEED / 1.8F; // unit per second

	public static final float MAX_VELOCITY = 8;

	private Controller controller;

	/* DIRECTION */

	public enum Direction {
		LEFT(270), UP(180), RIGHT(90), DOWN(0);

		// The corresponding angle
		public float angle;

		private Direction(float angle) {
			this.angle = angle;
		}
	}

	private Direction direction = Direction.RIGHT;

	/* STATE */

	private enum PangolinState {
		IDLE, WALKING, FALLING, DYING;
	}

	private PangolinState pangolinState;

	/* POSITION */

	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	private boolean controllerEnabled = true;

	public Pangolin(GravityPangolinWorld gravityPangolinWorld, float x, float y) {
		super(gravityPangolinWorld, 2);
		
		createGraphic(x, y);
		createBody(gravityPangolinWorld.getWorld(), x, y);
		createFeetSensor();
		controller = new Controller(gravityPangolinWorld, this);
	}

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
		
		for (Fixture fixture : body.getFixtureList())
			fixture.setUserData(this);
		body.setFixedRotation(true);
		body.setTransform(body.getPosition().add(origin), body.getAngle());
	}

	// The fixtures that come in contact with the feet
	private List<Entity> inContactEntities = new ArrayList<Entity>();
	private FeetSensor feetSensor;

	/**
	 * Initiate the feet sensor
	 */
	private void createFeetSensor() {
		PolygonShape polygonShape = new PolygonShape();
		FixtureDef myFixtureDef = new FixtureDef();
		myFixtureDef.shape = polygonShape;
		myFixtureDef.density = 1;
		myFixtureDef.filter.categoryBits = FilterMask.FEET_SENSOR;
		myFixtureDef.filter.maskBits = (short) (FilterMask.EVERYONE & ~FilterMask.STAR_FISH);

		polygonShape.setAsBox(0.8f, 0.05f, body.getLocalCenter().add(new Vector2(-0.1F, -0.3F)), 0);
		myFixtureDef.isSensor = true;
		
		Fixture feetSensorFixture = body.createFixture(myFixtureDef);
		feetSensor = new FeetSensor(gravityPangolinWorld, this);
		feetSensorFixture.setUserData(feetSensor);
	}

	public void createGraphic(float x, float y) {
		entityGraphic = new PangolinGraphic(this, gravityPangolinWorld,  x, y);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		
//		if (pangolinState == PangolinState.DYING) {
//			body.setLinearVelocity(new Vector2(0, 0));
//			return ;
//		}
		
		if (controllerEnabled)
			controller.update(delta);
		clear();
		((PangolinGraphic) entityGraphic).updateFrame();

		// Check if the Pangolin is out of screen
		if (GameUtil.isOutOfScreen(getX(), getY()))
			die();
		
		Direction gravityDirection = gravityPangolinWorld.getGravity().direction;
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

	public void die() {
		System.out.println("<(-.-)> ... The Pangolin's dead ... <(-.-)>");
		pangolinState = PangolinState.DYING;
		GravityPangolinGame.getInstance().restart();
	}

	@Override
	public void beginContact(Object entity, Fixture fixture) {
//		if (entity instanceof GravityChangerBlock)
//			return;
//		inContactEntities.add((Entity) entity);
	}

	@Override
	public void endContact(Object entity, Fixture fixture) {
//		inContactEntities.remove((Entity) entity);
	}

	/** EVENTS **/

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
		return feetSensor.isInContact();
//		return inContactEntities.size() > 0;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public void rotate(Direction direction) {
		body.setTransform(body.getPosition(), (float) Math.toRadians(direction.angle));
	}

	public Direction getDirection() {
		return direction;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
