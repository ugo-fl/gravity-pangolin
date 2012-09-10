package fr.gravity.pangolin.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.entity.Pangolin.Direction;

public class IdlePangolinSprite extends PangolinSprite {

	public IdlePangolinSprite(Pangolin pangolin) {
		super(pangolin);
		// TODO Auto-generated constructor stub
	}

//	private Pangolin pangolin;
//	
//	private static final int FRAME_COLS = 2;
//	private static final int FRAME_ROWS = 1;
//	
//	private TextureRegion left;
//	private TextureRegion right;
//	
//	public IdlePangolinState(Pangolin pangolin) {
//		super(pangolin);
//		this.pangolin = pangolin;
////		loadAnimation();
//	}
//
////	private void loadAnimation() {
////		Texture pangolinTexture = new Texture(
////				Gdx.files.internal("images/sprite_pangolin.png"));
////		TextureRegion[][] tmp = TextureRegion.split(pangolinTexture,
////				pangolinTexture.getWidth() / FRAME_COLS,
////				pangolinTexture.getHeight() / FRAME_ROWS);
////		tmp[0][0].flip(false, true);
////		right = tmp[0][0];
////		left = new TextureRegion(right);
////		left.flip(true, false);
////	}
//
//	@Override
//	public TextureRegion getFrame(float stateTime) {
//		Direction direction = pangolin.getDirection();
//		return 
//	}
	
}
