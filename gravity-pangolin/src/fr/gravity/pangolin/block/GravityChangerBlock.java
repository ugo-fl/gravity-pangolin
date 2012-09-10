package fr.gravity.pangolin.block;

import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import fr.gravity.pangolin.Gravity;
import fr.gravity.pangolin.Gravity.Side;

public class GravityChangerBlock extends Block {

	public static float WIDTH = 0.5F;
	public static float HEIGHT = 0.5F;

	private static final long DEACTIVATED_PERIOD = 1000;

	private Gravity gravity;
	private Side s1;
	private Side s2;

	private long timestamp;

	private TextureRegion textureRegion;

	public GravityChangerBlock(Vector2 pos, Gravity gravity, Side s1, Side s2) {
		super(pos);
		this.gravity = gravity;
		this.s1 = s1;
		this.s2 = s2;
		setSize(WIDTH, HEIGHT);
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
	public TextureRegion getTextureRegion() {
		if (textureRegion == null) {
			Texture gravityChangerTexture = new Texture(Gdx.files.internal("images/gravity_changer.png"));
			textureRegion = new TextureRegion(gravityChangerTexture);
		}
		return textureRegion;
	}

}
