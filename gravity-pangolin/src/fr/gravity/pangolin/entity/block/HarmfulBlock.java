package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.HarmfulBlockGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.util.CountDown;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class HarmfulBlock extends Entity {

	private final static float HEIGHT = 1;
	private final static float MIN_WIDTH = 0;
	private final static float MAX_WIDTH = 2;

	private Fixture fixture;
	private Direction direction;
	private float angle;

	public HarmfulBlock(GravityPangolinWorld gravityPangolinWorld, float x, float y, Direction direction) {
		super(gravityPangolinWorld, 2);

		this.direction = direction;

		createBody(gravityPangolinWorld.getWorld(), x, y);
		createGraphic(x, y);
	}

	protected void createBody(World world, float x, float y) {

		PolygonShape polygonShape = new PolygonShape();
		angle = (float) Math.toRadians((direction.angle + 90) % 360);
		polygonShape.setAsBox(MIN_WIDTH / 2, HEIGHT / 2, new Vector2(MIN_WIDTH / 2, HEIGHT / 2), angle);

		BodyDef bd = new BodyDef();
		bd.position.set(x, y + 1);
		bd.type = BodyType.KinematicBody;

		FixtureDef def = new FixtureDef();
		def.shape = polygonShape;
		def.density = 1;

		body = world.createBody(bd);
		fixture = body.createFixture(def);
		fixture.setUserData(this);
		origin = new Vector2(0, 0);
	}

	public void createGraphic(float x, float y) {
		entityGraphic = new HarmfulBlockGraphic(x, y);
		entityGraphic.rotate((direction.angle - 90 + 360) % 360);
	}

	private final static int MOVEMENT_DURATION = 1000;
	private CountDown countDown = new CountDown(MOVEMENT_DURATION);
	private boolean growing = false;
	private float currentWidth;

	@Override
	public void act(float delta) {
		super.act(delta);

		if (countDown.isFinished()) {
			countDown.start();
			growing = !growing;
		}

		float time = growing ? countDown.getTimeRemaining() : MOVEMENT_DURATION - countDown.getTimeRemaining();

		currentWidth = MIN_WIDTH + ((MAX_WIDTH - MIN_WIDTH) * (time / MOVEMENT_DURATION));
		float centerX = 0;
		float centerY = 0;
		switch (direction) {
		case DOWN:
			centerX = HEIGHT / 2;
			centerY = -(currentWidth / 2);
			break;
		case UP:
			centerX = HEIGHT / 2;
			centerY = (currentWidth / 2);
			break;
		case LEFT:
			centerX = -(currentWidth / 2);
			centerY = HEIGHT / 2;
			break;
		case RIGHT:
			centerX = (currentWidth / 2);
			centerY = HEIGHT / 2;
			break;
		}
		((PolygonShape) fixture.getShape()).setAsBox(currentWidth / 2, HEIGHT / 2, new Vector2(centerX, centerY), angle);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (entityGraphic == null)
			return;

		Vector2 bodyPos = body.getPosition().sub(origin);

		entityGraphic.setOrigin(origin.x, origin.y);

		entityGraphic.setBounds(direction == Direction.UP ? bodyPos.x + HEIGHT : bodyPos.x, direction == Direction.LEFT ? bodyPos.y + HEIGHT : bodyPos.y, currentWidth, HEIGHT);
		entityGraphic.setU((MAX_WIDTH - currentWidth) / MAX_WIDTH);

		entityGraphic.draw(batch);
	}

	@Override
	public void beginContact(Object entity, Fixture fixture) {
		if (entity instanceof Pangolin)
			gravityPangolinWorld.getPangolin().die();
	}

	@Override
	public void endContact(Object entity, Fixture fixture) {
	}

	@Override
	public void touchDown() {
	}

	@Override
	public void touchUp() {
	}

}
