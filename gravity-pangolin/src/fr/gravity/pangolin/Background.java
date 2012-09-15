package fr.gravity.pangolin;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.TextureLoader.TextureId;
import fr.gravity.pangolin.entity.EntityGraphic;

public class Background extends EntityGraphic {

	public Background() {
		TextureRegion textureRegion = TextureLoader.getInstance().getTextureRegions(TextureId.BACKGROUND)[0];
		set(new Sprite(textureRegion), 0, 0);
	}

}
