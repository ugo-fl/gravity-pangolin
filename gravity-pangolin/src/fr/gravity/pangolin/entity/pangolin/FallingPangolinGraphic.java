package fr.gravity.pangolin.entity.pangolin;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;

public class FallingPangolinGraphic extends PangolinGraphic {

	private Animation animation;

	public FallingPangolinGraphic(Pangolin pangolin, float x, float y) {
		super(pangolin, x, y);
		loadAnimation();
		set(getFrame(stateTime));
	}

	private void loadAnimation() {
		animation = new Animation(0.08f, TextureLoader.getInstance().getTextureRegions(TextureId.PANGOLIN_BALLMODE));
	}

	@Override
	public Sprite getFrame(float stateTime) {
		return new Sprite(animation.getKeyFrame(stateTime, true));
	}
	
	@Override
	protected void updateFrame() {
		setRegion(getFrame(stateTime));
	}

	@Override
	public void process() {
		updateFrame();
	}

}
