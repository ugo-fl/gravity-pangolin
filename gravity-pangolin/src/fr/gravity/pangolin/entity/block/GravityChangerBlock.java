package fr.gravity.pangolin.entity.block;

import java.util.Date;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.Gravity;
import fr.gravity.pangolin.Gravity.Side;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.GravityChangerBlockGraphic;

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
	public boolean collides() {
		Date date = new Date();
		if (date.getTime() - timestamp < DEACTIVATED_PERIOD)
			return false;
		timestamp = date.getTime();
		Side currentGravitySide = gravity.getSide();
		Side newGravitySide = Side.DOWN;

		if (currentGravitySide == Side.UP)
			newGravitySide = Side.RIGHT;
		else if (currentGravitySide == Side.DOWN)
			newGravitySide = Side.LEFT;
		else if (currentGravitySide == Side.LEFT)
			newGravitySide = Side.UP;
		gravity.setSide(newGravitySide);
		return false;
	}

	@Override
	public Rectangle getBoundingRectangle() {
		final float width = entityGraphic.getWidth() / 5;
		final float height = entityGraphic.getHeight() / 5;
		final float x = getX() + (entityGraphic.getWidth() / 2) - (width / 2);
		final float y = getY() + (entityGraphic.getHeight() / 2) - (height / 2);
		
		return new Rectangle(x, y, width, height);
	}
	
//	@Override
//	public void draw(SpriteBatch spriteBatch) {
//		entityGraphic.draw(spriteBatch);
//	}

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
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}
