package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.constant.FilterMask;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.GravityChangerBlockGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.util.CountDown;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class GravityChangerBlock extends Entity {

	// This countdown prevents from multiple gravity changes at a time
	// when the player crosses it
	private static final int DEACTIVATED_PERIOD = 1000;
	private CountDown countDown = new CountDown(DEACTIVATED_PERIOD);

	public GravityChangerBlock(GravityPangolinWorld gravityPangolinWorld, float x, float y) {
		super(gravityPangolinWorld, 1);

		createGraphic(x, y);
		createBody(gravityPangolinWorld.getWorld(), x, y);
	}

	public void createGraphic(float x, float y) {
		entityGraphic = new GravityChangerBlockGraphic(x, y);
	}

	protected void createBody(World world, float x, float y) {
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(0.25F);

		BodyDef bd = new BodyDef();
		bd.position.set(x, y);
		bd.type = BodyType.StaticBody;

		FixtureDef def = new FixtureDef();
		def.shape = circleShape;
		def.isSensor = true;

		// Filtering
		def.filter.categoryBits = FilterMask.GRAVITY_CHANGER;

		body = world.createBody(bd);
		Fixture fixture = body.createFixture(def);
		fixture.setUserData(this);

		origin = new Vector2(0.5F, 0.5F);
	}

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub
	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}

	@Override
	public void beginContact(Object entity, Fixture fixture) {
		if (!(entity instanceof Pangolin))
			return;

		if (!countDown.isFinished())
			return;
		countDown.start();

		gravityPangolinWorld.nextGravity();
	}

	@Override
	public void endContact(Object entity, Fixture fixture) {
		// TODO Auto-generated method stub

	}

}
