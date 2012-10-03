package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.GravityChangerBlockGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.game.CountDown;
import fr.gravity.pangolin.world.Gravity;

public class GravityChangerBlock extends Entity {

	private static final int DEACTIVATED_PERIOD = 1000;
	private CountDown waiter = new CountDown(DEACTIVATED_PERIOD);
	
	private Gravity gravity;

//	private long timestamp;

	public GravityChangerBlock(float x, float y, Gravity gravity) {
		this.gravity = gravity;
		entityGraphic = new GravityChangerBlockGraphic(x, y);
	}

	/**
	 * Change the gravity on collision. On the first collision the
	 * GravityChanger is deactivated for DEACTIVATED_PERIOD milliseconds.
	 */
	@Override
	public Rectangle getBoundingRectangle() {
		final float width = entityGraphic.width / 5;
		final float height = entityGraphic.height / 5;
		final float x = entityGraphic.x + (entityGraphic.width / 2) - (width / 2);
		final float y = entityGraphic.y + (entityGraphic.height / 2) - (height / 2);

		return new Rectangle(x, y, width, height);
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

	@Override
	public Entity collides() {
		if (!waiter.waitForIt())
			return null;
		waiter.start();

		Direction[] directionValues = Direction.values();
		Direction currentGravityDirection = gravity.getDirection();
		Direction newGravityDirection;
		int ordinal = currentGravityDirection.ordinal() + 1;
		newGravityDirection = directionValues[ordinal % directionValues.length];
		gravity.setDirection(newGravityDirection);
		return null;
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}
}
