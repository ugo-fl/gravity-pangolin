package fr.gravity.pangolin.entity.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;
import fr.gravity.pangolin.util.SpriteUtil;
import fr.gravity.pangolin.world.PangolinWorld;

public abstract class PangolinGraphic extends EntityGraphic {

	protected Pangolin pangolin;

	private TextureRegion[] textureRegions = TextureHelper.getInstance()
			.getTextureRegions(TextureId.PANGOLIN);
	protected Animation animation = new Animation(0.25F, textureRegions);

	public PangolinGraphic(Pangolin pangolin, float x, float y) {
		super(TextureHelper.getInstance().getTextureRegions(TextureId.PANGOLIN)[0], x, y);
		this.pangolin = pangolin;
	}

	protected Sprite getFrame(float stateTime) {
		Direction direction = PangolinWorld.getInstance().getGravity().getDirection();
		if (animation != null) {
			Sprite sprite = new Sprite(animation.getKeyFrame(stateTime, true));
			adjustSpriteSide(sprite, direction, pangolin.getDirection());
			return sprite;
		}
		return null;
	}

	private void adjustSpriteSide(Sprite sprite, Direction gravityDirection, Direction pangolinDirection) {
		if (gravityDirection == Direction.RIGHT) {
			sprite.flip(pangolinDirection == Direction.UP, true);
			SpriteUtil.rotate(sprite, true);
		} else if (gravityDirection == Direction.LEFT) {
			sprite.flip(pangolinDirection == Direction.DOWN, true);
			SpriteUtil.rotate(sprite, false);
		} else if (gravityDirection == Direction.UP) {
			sprite.flip(pangolinDirection == Direction.LEFT, true);
		} else if (gravityDirection == Direction.DOWN) {
			sprite.flip(pangolinDirection == Direction.LEFT, false);
		}
	}

	protected void updateFrame() {
		Sprite frame = getFrame(stateTime);
		stateTime += Gdx.graphics.getDeltaTime();
		if (frame != null)
			setRegion(frame);
	}

	@Override
	public void draw(SpriteBatch spriteBatch, float stateTime) {
		process();
		super.draw(spriteBatch, stateTime);
	}

	/**
	 * Abstract methods
	 */

	public abstract void process();

}
