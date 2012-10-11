package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.GravityChangerBlockGraphic;
import fr.gravity.pangolin.util.CountDown;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class GravityChangerBlock extends Entity {

	private static final int DEACTIVATED_PERIOD = 1000;
	private CountDown countDown = new CountDown(DEACTIVATED_PERIOD);
	
//	private long timestamp;

	public GravityChangerBlock(World world, float x, float y) {
		super(world, x, y, 1);
	}

	/**
	 * Change the gravity on collision. On the first collision the
	 * GravityChanger is deactivated for DEACTIVATED_PERIOD milliseconds.
	 */
//	@Override
//	public Rectangle getBoundingRectangle() {
//		final float width = entityGraphic.width / 5;
//		final float height = entityGraphic.height / 5;
//		final float x = entityGraphic.x + (entityGraphic.width / 2) - (width / 2);
//		final float y = entityGraphic.y + (entityGraphic.height / 2) - (height / 2);
//
//		return new Rectangle(x, y, width, height);
//	}

	@Override
	public void createGraphic(float x, float y) {
		entityGraphic = new GravityChangerBlockGraphic(x, y);
	}

	@Override
	protected void createBody(World world, float x, float y) {
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(1);
		
	    BodyDef bd = new BodyDef();
	    bd.position.set(x, y);
	    bd.type = BodyType.StaticBody;
	    
	    FixtureDef def = new FixtureDef();
	    def.shape = circleShape;
	    def.density = 0;
	    def.friction = 0;
	    def.restitution = 0;
	    def.isSensor = true;
	    
	    body = world.createBody(bd);
	    Fixture fixture = body.createFixture(def);
	    fixture.setUserData(this);
	    
	    origin = new Vector2(0, 0);
	}
	
	@Override
	public void touchDown() {
		// TODO Auto-generated method stub
	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub
	}

//	@Override
//	public void draw(SpriteBatch batch, float parentAlpha) {
//		entityGraphic.draw(batch);
//	}

//	@Override
//	public Entity collides() {
//		if (!waiter.waitForIt())
//			return null;
//		waiter.start();
//
//		Direction[] directionValues = Direction.values();
//		Direction currentGravityDirection = gravity.getDirection();
//		Direction newGravityDirection;
//		int ordinal = currentGravityDirection.ordinal() + 1;
//		newGravityDirection = directionValues[ordinal % directionValues.length];
//		gravity.setDirection(newGravityDirection);
//		return null;
//	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}

	@Override
	public void beginContact(Object entity) {
		if (!countDown.isFinished())
			return ;
		countDown.start();

		GravityPangolinWorld.getInstance().nextGravity();
	}

	@Override
	public void endContact(Object entity) {
		// TODO Auto-generated method stub
		
	}

}
