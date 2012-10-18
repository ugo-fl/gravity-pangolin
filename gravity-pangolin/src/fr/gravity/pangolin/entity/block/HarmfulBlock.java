package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.HarmfulBlockGraphic;
import fr.gravity.pangolin.util.CountDown;
import fr.gravity.pangolin.util.GameUtil;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class HarmfulBlock extends Entity {

	private Fixture fixture;

	private final static float HEIGHT = 1;
	private final static float MIN_WIDTH = 0;
	private final static float MAX_WIDTH = 2;

	public HarmfulBlock(GravityPangolinWorld gravityPangolinWorld, float x, float y) {
		super(gravityPangolinWorld, 2);

		createGraphic(x, y);
		createBody(gravityPangolinWorld.getWorld(), x, y);
	}

	public void createGraphic(float x, float y) {
		entityGraphic = new HarmfulBlockGraphic(x, y);
	}

	protected void createBody(World world, float x, float y) {

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(MIN_WIDTH / 2, HEIGHT / 2, new Vector2(MIN_WIDTH / 2, HEIGHT / 2), 0);

		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = BodyType.KinematicBody;

		FixtureDef def = new FixtureDef();
		def.shape = polygonShape;
		def.density = 1;

		body = world.createBody(bd);
		fixture = body.createFixture(def);
		fixture.setUserData(this);
		origin = new Vector2(0, 0);
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
		((PolygonShape) fixture.getShape()).setAsBox(currentWidth / 2, HEIGHT / 2, new Vector2((currentWidth / 2), HEIGHT / 2), 0);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		if (entityGraphic == null)
			return;

		Vector2 bodyPos = body.getPosition().sub(origin);

		entityGraphic.setPosition(bodyPos.x, bodyPos.y);
		entityGraphic.setOrigin(origin.x, origin.y);
		// entityGraphic.setRotation(body.getAngle() *
		// MathUtils.radiansToDegrees);
		System.out.println("HUH: " + (scale - currentWidth));
//		entityGraphic.setSize(scale, scale * entityGraphic.getHeight() / entityGraphic.getWidth());
		
		entityGraphic.setBounds(bodyPos.x, bodyPos.y, currentWidth, HEIGHT);
		entityGraphic.setU((MAX_WIDTH - currentWidth) / MAX_WIDTH);
		
		entityGraphic.draw(batch);

		// batch.draw(entityGraphic.getTexture(), bodyPos.x, bodyPos.y, 0F, 0F,
		// width, HEIGHT, scale, scale, 0, (int) (currentWidth - MIN_WIDTH),
		// (int)0,
		// (int) entityGraphic.getWidth(), (int) entityGraphic.getHeight(),
		// false, false);
		// draw(entityGraphic.getTexture(), bodyPos.x, bodyPos.y, currentWidth,
		// HEIGHT);
	}

	@Override
	public void beginContact(Object entity) {
		gravityPangolinWorld.getPangolin().die();
	}

	@Override
	public void endContact(Object entity) {
	}

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub

	}

}
