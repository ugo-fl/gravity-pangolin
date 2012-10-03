package fr.gravity.pangolin.entity.graphic;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;

public class FallingPangolinGraphic extends PangolinGraphic {

	private Animation animation;

	public FallingPangolinGraphic(Pangolin pangolin, float x, float y) {
		super(pangolin, x, y);
		loadAnimation();
		setRegion(getFrame(stateTime));
	}

	private void loadAnimation() {
		animation = new Animation(0.08f, TextureHelper.getInstance()
				.getTextureRegions(TextureId.PANGOLIN_BALLMODE));
	}

	@Override
	public Sprite getFrame(float stateTime) {
		Sprite sprite;
		if (animation != null)
			sprite = new Sprite(animation.getKeyFrame(stateTime, true));
		else
			sprite = new Sprite(super.animation.getKeyFrame(stateTime, true));
		return sprite;
	}

	@Override
	protected void updateFrame() {
		setRegion(getFrame(stateTime));
	}

	@Override
	public void process() {
		updateFrame();
	}

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchDownOut() {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchUpOut() {
		// TODO Auto-generated method stub

	}

	// @Override
	// public Rectangle getBoundingRectangle(){
	// return new Rectangle(0, 0, 1, 1);
	// }

}
