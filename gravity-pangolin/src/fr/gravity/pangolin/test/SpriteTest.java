package fr.gravity.pangolin.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.screen.ScreenAbstract;
import fr.gravity.pangolin.util.GameUtil;

public class SpriteTest extends Sprite { 

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
				// Flip and rotate
//				sprite.setScale(-0.1F, -0.1F);
//				sprite.flip(flipX, flipY);
				walkFrames[index++] = sprite;
			}
		}
		
		set(walkFrames[0]);
		setPosition(1, -4);
		
		ScreenAbstract screen = GameUtil.getScreen();
		setSize(Math.abs(getRegionWidth()) / screen.getPpuX(), getRegionHeight() / screen.getPpuY());
//		init(walkFrames[0], 1, 1);
		
	}
	
}
