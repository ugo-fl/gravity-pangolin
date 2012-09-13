package fr.gravity.pangolin;

import fr.gravity.pangolin.TextureLoader.TextureId;
import fr.gravity.pangolin.entity.EntityGraphic;

public class Background extends EntityGraphic {

	public Background() {
		init(TextureLoader.getInstance().getTextureRegions(TextureId.BACKGROUND)[0], 0, 0);
	}

}
