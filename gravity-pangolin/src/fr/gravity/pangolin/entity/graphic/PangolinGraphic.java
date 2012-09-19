package fr.gravity.pangolin.entity.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import fr.gravity.pangolin.Gravity.Side;
import fr.gravity.pangolin.PangolinWorld;
import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;
import fr.gravity.pangolin.entity.EntityGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.util.SpriteUtil;

public abstract class PangolinGraphic extends EntityGraphic {

	protected Pangolin pangolin;

	private TextureRegion[] textureRegions = TextureLoader.getInstance()
			.getTextureRegions(TextureId.PANGOLIN);
	protected Animation animation = new Animation(0.25F, textureRegions);

	public PangolinGraphic(Pangolin pangolin, float x, float y) {
		this.pangolin = pangolin;
		set(getFrame(stateTime), x, y);
	}

	public Sprite getFrame(float stateTime) {
		Side side = PangolinWorld.getInstance().getGravity().getSide();
		if (animation != null) {
			Sprite sprite = new Sprite(animation.getKeyFrame(stateTime, true));
			adjustSpriteSide(sprite, side, pangolin.getDirection());
			return sprite;
		}
		return null;
	}

	private void adjustSpriteSide(Sprite sprite, Side side, Direction direction) {
		if (side == Side.RIGHT) {
			sprite.flip(direction == Direction.UP, true);
			SpriteUtil.rotate(sprite, true);
		} else if (side == Side.LEFT) {
			sprite.flip(direction == Direction.DOWN, true);
			SpriteUtil.rotate(sprite, false);
		} else if (side == Side.UP) {
			sprite.flip(direction == Direction.LEFT, true);
		} else if (side == Side.DOWN) {
			sprite.flip(direction == Direction.LEFT, false);
		}
	}

	protected void updateFrame() {
		Sprite frame = getFrame(stateTime);
		if (frame != null)
			set(frame);
	}

	public void draw(SpriteBatch spriteBatch) {
		stateTime += Gdx.graphics.getDeltaTime();
		process();
		super.draw(spriteBatch);
	}

	// TODO JUST FOR TESTING. ALL THE SPRITES NEED TO HAVE SAME SIZE.
	@Override
	public Rectangle getBoundingRectangle(){
		return new Rectangle(getX(), getY(), 1, 1);
	}
	
	/**
	 * Abstract methods
	 */

	public abstract void process();

}
