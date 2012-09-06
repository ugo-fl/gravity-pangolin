package fr.gravity.pangolin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Background {

	private TextureRegion textureRegion;
	
	public Background() {
		Texture backgroundTexture = new Texture(
				Gdx.files.internal("images/background.png"));
		textureRegion = new TextureRegion(backgroundTexture);
		textureRegion.flip(false, true);
	}

	public TextureRegion getTextureRegion() {
		return textureRegion;
	}

}
