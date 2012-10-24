package fr.gravity.pangolin.entity.graphic;

import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;

public class ExitBlockGraphic extends EntityGraphic {

	public ExitBlockGraphic(float x, float y, Direction direction) {
		super(TextureHelper.getInstance().getTextureRegions(TextureId.EXIT)[0], x, y);

//		if (direction == Direction.UP) {
//			flip(false, true);
//		}
		
//		this.originX = this.width / 2;
//		this.originY = this.height / 2;
//		
//		if (direction == Direction.LEFT)
//			ImageUtil.rotate90(this, false);
//		else if (direction == Direction.RIGHT) {
////			ImageUtil.rotate90(this, true);
//			action(RotateBy.$(360, 5F));
//			
////			this.x = GameUtil.projectCoordinateX(x + 1);
//		} else if (direction == Direction.UP) {
//			rotation = 180;
//			this.y++;
//		}

		// Sprite sprite = new
		// Sprite(TextureHelper.getInstance().getTextureRegions(TextureId.EXIT)[0]);
		// if (direction == Direction.LEFT)
		// SpriteUtil.rotate(sprite, true);
		// else if (direction == Direction.RIGHT) {
		// SpriteUtil.rotate(sprite, false);
		// x++;
		// }
		// else if (direction == Direction.UP) {
		// sprite.rotate(180);
		// y++;
		// }
		// set(sprite, x, y);
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
