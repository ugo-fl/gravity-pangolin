package fr.gravity.pangolin.entity.graphic.pangolin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.entity.graphic.EntityGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;
import fr.gravity.pangolin.util.SpriteUtil;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class PangolinGraphic extends EntityGraphic {

	protected Pangolin pangolin;
	private boolean idle = false;

	private TextureRegion[] textureRegions = TextureHelper.getInstance().getTextureRegions(TextureId.PANGOLIN);
	protected Animation animation = new Animation(0.25F, textureRegions);

	public PangolinGraphic(Pangolin pangolin, float x, float y) {
		super(TextureHelper.getInstance().getTextureRegions(TextureId.PANGOLIN)[0], x, y);
		this.pangolin = pangolin;
	}

	protected Sprite getFrame(float stateTime) {
		Direction direction = GravityPangolinWorld.getInstance().getGravity().direction;
		if (animation != null) {
			Sprite sprite = new Sprite(animation.getKeyFrame(stateTime, true));
			adjustSpriteSide(sprite, direction, pangolin.getDirection());
			return sprite;
		}
		return null;
	}

	private void adjustSpriteSide(Sprite sprite, Direction gravityDirection, Direction pangolinDirection) {
		if (gravityDirection == Direction.RIGHT) {
			sprite.flip(pangolinDirection == Direction.UP, false);
			// SpriteUtil.rotate(sprite, false);
		} else if (gravityDirection == Direction.LEFT) {
			sprite.flip(pangolinDirection == Direction.DOWN, false);
			// SpriteUtil.rotate(sprite, false);
		} else if (gravityDirection == Direction.UP) {
			sprite.flip(pangolinDirection == Direction.RIGHT, false);
		} else if (gravityDirection == Direction.DOWN) {
			sprite.flip(pangolinDirection == Direction.LEFT, false);
		}
	}

	public void updateFrame() {
		Sprite frame = getFrame(stateTime);
		if (idle)
			stateTime = 0;
		else
			stateTime += Gdx.graphics.getDeltaTime();
		if (frame != null)
			set(frame);
	}

	public void idle() {
		idle = true;
	}
	
	public void move() {
		idle = false;
	}
	
	@Override
	public void touchDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchDownOut() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchUpOut() {
		// TODO Auto-generated method stub

	}

}
