package fr.gravity.pangolin.entity.mob;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import fr.gravity.pangolin.constant.FilterMask;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.ProjectileGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.util.CountDown;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class Projectile extends Entity {

	private static float SIZE = 0.25F;

	private Pangolin pangolin;

	public Projectile(GravityPangolinWorld gravityPangolinWorld, float x, float y) {
		super(gravityPangolinWorld, 0.5F);

		this.pangolin = gravityPangolinWorld.getPangolin();
		createBody(gravityPangolinWorld.getWorld(), x, y);
		createGraphic(x, y);
	}

	public void createBody(World world, float x, float y) {
		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = BodyType.DynamicBody;

		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(SIZE);

		FixtureDef def = new FixtureDef();
		def.shape = circleShape;
		def.density = 1;
		def.friction = 0;
		def.filter.categoryBits = FilterMask.PROJECTILE;
		def.filter.maskBits = (short) (FilterMask.EVERYONE & ~FilterMask.STAR_FISH & ~FilterMask.FEET_SENSOR & ~FilterMask.GRAVITY_CHANGER);

		body = world.createBody(bd);
		Fixture fixture = body.createFixture(def);
		fixture.setUserData(this);

		origin = new Vector2(SIZE, SIZE);
	}

	public void createGraphic(float x, float y) {
		entityGraphic = new ProjectileGraphic(x, y);
	}

	private static final float SPEED = 4;
	private CountDown reAimCountDown = new CountDown(100);
	private Vector2 velocity = new Vector2();

	@Override
	public void act(float delta) {
		super.act(delta);

		if (reAimCountDown.isFinished()) {
			reAimCountDown.start();
			float dst = new Vector2(getX(), getY()).dst(pangolin.getX(), pangolin.getY());
			velocity = new Vector2((pangolin.getX() - getX()) / dst, (pangolin.getY() - getY()) / dst);
			velocity.mul(SPEED);
		}
		body.setLinearVelocity(velocity);
	}

	@Override
	public void beginContact(Object entity, Fixture fixture) {
		System.out.println("PROJECTILE IN CONTACT WITH " + entity);
		if (entity instanceof Pangolin)
			pangolin.die();
		gravityPangolinWorld.remove(this);
	}

	@Override
	public void endContact(Object entity, Fixture fixture) {
		// TODO Auto-generated method stub

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
