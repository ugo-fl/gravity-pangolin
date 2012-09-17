package fr.gravity.pangolin.entity.block;

import java.util.Date;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.Gravity;
import fr.gravity.pangolin.Gravity.Side;
import fr.gravity.pangolin.entity.Entity;

public class GravityChangerBlock extends Entity {

	private static final long DEACTIVATED_PERIOD = 1000;

	private Gravity gravity;
	private Side s1;
	private Side s2;

	private long timestamp;

	private TextureRegion textureRegion;

	public GravityChangerBlock(float x, float y, Gravity gravity, Side s1, Side s2) {
		this.gravity = gravity;
		this.s1 = s1;
		this.s2 = s2;
		
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
		Side newGravitySide;

		if (currentGravitySide == s1
				|| currentGravitySide == Gravity.getOppositeSide(s1))
			newGravitySide = s2;
		else
			newGravitySide = s1;
		gravity.setSide(newGravitySide);
		return false;
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		
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
