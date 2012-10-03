package fr.gravity.pangolin.entity.graphic;

import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.gravity.pangolin.entity.EntityGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;
import fr.gravity.pangolin.util.SpriteUtil;

public class ExitBlockGraphic extends EntityGraphic {

	public ExitBlockGraphic(float x, float y, Direction direction) {
		Sprite sprite = new Sprite(TextureHelper.getInstance().getTextureRegions(TextureId.EXIT)[0]);
		if (direction == Direction.LEFT)
			SpriteUtil.rotate(sprite, true);
		else if (direction == Direction.RIGHT) {
			SpriteUtil.rotate(sprite, false);
			x++;
		}
		else if (direction == Direction.UP) {
			sprite.rotate(180);
			y++;
		}
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
