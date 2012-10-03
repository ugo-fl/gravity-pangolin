package fr.gravity.pangolin.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;

public class PangolinExitAnimation extends Animation {

	public PangolinExitAnimation() {
		super(0.75F, TextureHelper.getInstance().getTextureRegions(TextureId.PANGOLIN_BALLMODE));
	}

	@Override
	public TextureRegion getKeyFrame (float stateTime) {
		TextureRegion result = super.getKeyFrame(stateTime);
		return result;
	}
}
