package fr.gravity.pangolin.entity.block;

import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;
import fr.gravity.pangolin.entity.EntityGraphic;
import fr.gravity.pangolin.entity.block.ExitBlock.ExitSide;

public class ExitBlockGraphic extends EntityGraphic {

	public static float WIDTH = 1F;
	public static float HEIGHT = 0.3F;
	
	public ExitBlockGraphic(float x, float y, ExitSide exitSide) {
//		getBoundingRectangle().setY(getBoundingRectangle().y + 0.8F);
//		setSize(WIDTH, HEIGHT);
		init(TextureLoader.getInstance().getTextureRegions(TextureId.EXIT)[0], x, y);
	}

}
