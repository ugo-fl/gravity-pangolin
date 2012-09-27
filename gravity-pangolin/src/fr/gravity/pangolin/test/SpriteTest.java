package fr.gravity.pangolin.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.entity.EntityGraphic;
import fr.gravity.pangolin.util.SpriteUtil;

public class SpriteTest extends EntityGraphic { 

	private static final int FRAME_COLS = 2;
	private static final int FRAME_ROWS = 1;
	
	public SpriteTest() {
		
		Texture pangolinTexture = new Texture("images/sprite_pangolin.png");
		
		TextureRegion[][] tmp = TextureRegion.split(pangolinTexture, pangolinTexture.getWidth() / FRAME_COLS,
				pangolinTexture.getHeight() / FRAME_ROWS);
		Sprite[] walkFrames = new Sprite[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				Sprite sprite = new Sprite(tmp[i][j]);
				
				SpriteUtil.rotate(sprite, false);
				
//				sprite.rotate90(false);
//				float width = sprite.getWidth();
//				float height = sprite.getHeight();
//				
//				sprite.setSize(height, width);
				
				// Flip and rotate
//				sprite.setScale(-0.1F, -0.1F);
//				sprite.flip(flipX, flipY);
//				sprite.rotate90(false);
				walkFrames[index++] = sprite;
			}
		}
		Animation animation = new Animation(0.25F, walkFrames);
		set(new Sprite(animation.getKeyFrame(0)), 10, 10);
		
//		ScreenAbstract screen = GameUtil.getScreen();
//		setSize(Math.abs(getRegionWidth()) / screen.getPpuX(), getRegionHeight() / screen.getPpuY());
//		setSize(Math.abs(getRegionWidth()) / screen.getPpuX(), getRegionHeight() / screen.getPpuY());
//		init(walkFrames[0], 1, 1);
		
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
