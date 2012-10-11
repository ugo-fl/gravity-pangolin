package fr.gravity.pangolin.entity.pangolin;

import java.util.ArrayList;
import java.util.List;

import test.BodyEditorLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.block.ExitBlock;
import fr.gravity.pangolin.entity.graphic.pangolin.PangolinGraphic;
import fr.gravity.pangolin.game.Controller;
import fr.gravity.pangolin.game.GravityPangolinGame;

public class Pangolin extends Entity {

	public static final float SPEED = 0.8F; // unit per second
	public static final float FALLING_SPEED = SPEED / 1.8F; // unit per second

	public static final float MAX_VELOCITY = 8;

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
			pangolinGraphic.setX(x);
			pangolinGraphic.setY(y);
		}
	}

	private PangolinState pangolinState;

	/* POSITION */

	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	private boolean landed = false;
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
		origin = loader.getOrigin("pangolin", scale).cpy();

		body.setFixedRotation(true);
		body.setUserData(this);

		body.getFixtureList().get(0);
	}

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

//		world.setContactListener(new ContactListener() {
//			@Override
//			public void preSolve(Contact contact, Manifold oldManifold) {
//			}
//
//			@Override
//			public void postSolve(Contact contact, ContactImpulse impulse) {
//			}
//
//			@Override
//			public void endContact(Contact contact) {
//				Fixture fixtureA = contact.getFixtureA();
//				Fixture fixtureB = contact.getFixtureB();
//
//				if (fixtureA == feetSensorFixture || fixtureB == feetSensorFixture) {
//					Fixture contactFixture = (feetSensorFixture == fixtureA ? fixtureB : fixtureA);
//					inContactEntities.remove(contactFixture);
//				}
//			}
//
//			@Override
//			public void beginContact(Contact contact) {
//				Fixture fixtureA = contact.getFixtureA();
//				Fixture fixtureB = contact.getFixtureB();
//
//				if (fixtureA == feetSensorFixture || fixtureB == feetSensorFixture) {
//					Fixture contactFixture = (feetSensorFixture == fixtureA ? fixtureB : fixtureA);
//					
//					if (contactFixture.getUserData() instanceof ExitBlock) {
//						GravityPangolinGame.getInstance().nextStage();
//					}
//					inContactEntities.add(contactFixture);
//				}
//			}
//		});
	}

	@Override
	public void createGraphic(float x, float y) {
		entityGraphic = new PangolinGraphic(this, x, y);
		controller = new Controller(GravityPangolinGame.getInstance().getPangolinWorld(), this);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (controllerEnabled)
			controller.update(delta);
		clear();
		((PangolinGraphic) entityGraphic).updateFrame();
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
		if (landed)
			pangolinState = PangolinState.WALKING;
		this.direction = direction;
		move(direction, false);
	}

	public void fall(Direction direction) {
		landed = false;
		pangolinState = PangolinState.FALLING;

		// TODO this code might not be real clean
		body.setTransform(body.getWorldCenter().add(direction == Direction.DOWN ? -1 : 1, direction == Direction.DOWN ? -0.5F : 0.5F),
				(float) Math.toRadians(direction == Direction.DOWN ? 0 : 180));
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
		inContactEntities.add((Entity) entity);
	}
	
	@Override
	public void endContact(Object entity) {
		inContactEntities.remove((Entity) entity);
	}

	/** EVENTS **/

//	@Override
//	public Entity collides() {
//		return null;
//	}

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

	public Direction getDirection() {
		return direction;
	}

}
