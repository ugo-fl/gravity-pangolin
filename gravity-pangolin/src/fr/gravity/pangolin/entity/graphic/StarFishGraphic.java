package fr.gravity.pangolin.entity.graphic;

import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;

public class StarFishGraphic extends EntityGraphic {

	public StarFishGraphic(float x, float y) {
		super(TextureHelper.getInstance().getSingleSprite(TextureId.STAR_FISH), x, y);
	}
	
	@Override
	public void touchDown() {
		
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
