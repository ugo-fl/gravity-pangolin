package fr.gravity.pangolin.entity.block;

import java.util.Date;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.Gravity;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.GravityChangerBlockGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;

public class GravityChangerBlock extends Entity {

	private static final long DEACTIVATED_PERIOD = 1000;

	private Gravity gravity;

	private long timestamp;

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
		final float width = entityGraphic.getWidth() / 5;
		final float height = entityGraphic.getHeight() / 5;
		final float x = entityGraphic.getX() + (entityGraphic.getWidth() / 2) - (width / 2);
		final float y = entityGraphic.getY() + (entityGraphic.getHeight() / 2) - (height / 2);

		return new Rectangle(x, y, width, height);
	}

	// @Override
	// public void draw(SpriteBatch spriteBatch) {
	// entityGraphic.draw(spriteBatch);
	// }

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		entityGraphic.draw(batch);
	}

	@Override
	public Entity collides() {
		Date date = new Date();
		if (date.getTime() - timestamp < DEACTIVATED_PERIOD)
			return this;
		timestamp = date.getTime();
		Direction currentGravityDirection = gravity.getDirection();
		Direction newGravityDirection = Direction.DOWN;

		if (currentGravityDirection == Direction.UP)
			newGravityDirection = Direction.RIGHT;
		else if (currentGravityDirection == Direction.DOWN)
			newGravityDirection = Direction.LEFT;
		else if (currentGravityDirection == Direction.LEFT)
			newGravityDirection = Direction.UP;
		gravity.setDirection(newGravityDirection);
		return this;
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}
}
