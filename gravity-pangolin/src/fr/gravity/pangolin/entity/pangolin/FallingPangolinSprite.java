package fr.gravity.pangolin.entity.pangolin;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FallingPangolinSprite extends PangolinGraphic {

	public static final float WIDTH = 1f;
	public static final float HEIGHT = 1f;

	private Animation animation;

	private static final int FRAME_COLS = 4;
	private static final int FRAME_ROWS = 1;

	public FallingPangolinSprite(Pangolin pangolin) {
		super(pangolin);
		loadAnimation();
	}

	private void loadAnimation() {
		Texture pangolinTexture = new Texture("images/sprite_pangolin_ballmode.png");
		TextureRegion[][] tmp = TextureRegion.split(pangolinTexture, pangolinTexture.getWidth() / FRAME_COLS,
				pangolinTexture.getHeight() / FRAME_ROWS);
		Sprite[] walkFrames = new Sprite[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				Sprite sprite = new Sprite(tmp[i][j]);
				sprite.flip(false, true);
				walkFrames[index++] = sprite;
			}
		}
		animation = new Animation(0.08f, walkFrames);
	}

	@Override
	public TextureRegion getFrame(float stateTime) {
		return animation.getKeyFrame(stateTime, true);
	}

//	@Override
//	public void draw(SpriteBatch spriteBatch, float stateTime) {
//		spriteBatch.draw(getFrame(stateTime), pangolin.getPosition().x, pangolin.getPosition().y, WIDTH, HEIGHT);
//	}

	@Override
	public void process() {
		setRegion(getFrame(stateTime));
		setRegionWidth((int) WIDTH);
		setRegionHeight((int) HEIGHT);
	}

}
