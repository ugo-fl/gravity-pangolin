package fr.gravity.pangolin;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureLoader {

	// Singleton instance
	private static TextureLoader instance;
	
	/** TEXTURES AND SIZE**/
	
	// Buttons
	private static final int BUTTONS_FRAME_COLS = 2;
	private static final int BUTTONS_FRAME_ROWS = 1;
	private TextureRegion[] buttonTextureRegions = new TextureRegion[BUTTONS_FRAME_COLS * BUTTONS_FRAME_ROWS];
	
	
	
	public static TextureLoader getInstance() {
		if (instance == null)
			instance = new TextureLoader();
		return instance;
	}
	
	private TextureLoader() {
		loadButtonSprites();
	}
	
	private void loadButtonSprites() {
		Texture buttonsTexture = new Texture("images/buttons_sprite.png");
		TextureRegion[][] tmp = TextureRegion.split(buttonsTexture, buttonsTexture.getWidth() / BUTTONS_FRAME_COLS,
				buttonsTexture.getHeight() / BUTTONS_FRAME_ROWS);
		TextureRegion[] frames = new TextureRegion[BUTTONS_FRAME_COLS * BUTTONS_FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < BUTTONS_FRAME_ROWS; i++) {
			for (int j = 0; j < BUTTONS_FRAME_COLS; j++) {
				buttonTextureRegions[index++] = tmp[i][j];
			}
		}
	}

	/** GETTERS **/
	
	public TextureRegion[] getButtonTextureRegions() {
		return buttonTextureRegions;
	}
	
}
