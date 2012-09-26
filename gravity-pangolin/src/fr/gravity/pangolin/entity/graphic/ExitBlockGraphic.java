package fr.gravity.pangolin.entity.graphic;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;
import fr.gravity.pangolin.entity.EntityGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;

public class ExitBlockGraphic extends EntityGraphic {

//	public static float WIDTH = 1F;
//	public static float HEIGHT = 0.3F;
	
	public ExitBlockGraphic(float x, float y, Direction direction) {
		Sprite sprite = new Sprite(TextureLoader.getInstance().getTextureRegions(TextureId.EXIT)[0]);
		set(sprite, x, y);
	}

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchDownOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchUpOut() {
		// TODO Auto-generated method stub
		
	}

}
