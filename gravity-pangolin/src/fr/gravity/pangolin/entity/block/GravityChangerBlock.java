package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.GravityChangerBlockGraphic;
import fr.gravity.pangolin.helper.CountDownHelper;
import fr.gravity.pangolin.world.GravityPangolinWorld.Gravity;

public class GravityChangerBlock extends Entity {

	private static final int DEACTIVATED_PERIOD = 1000;
	private CountDownHelper waiter = new CountDownHelper(DEACTIVATED_PERIOD);
	
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

}
