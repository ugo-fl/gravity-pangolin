package fr.gravity.pangolin.entity.graphic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;
import fr.gravity.pangolin.entity.EntityGraphic;
import fr.gravity.pangolin.util.SpriteUtil;

public class ButtonGraphic extends EntityGraphic {

	private Sprite button;
	private Sprite buttonHover;
	
	public ButtonGraphic(float x, float y) {
		TextureRegion[] buttons = TextureLoader.getInstance().getTextureRegions(TextureId.BUTTONS);
		button = SpriteUtil.generateSprite(buttons[0], x, y, false);
		buttonHover = SpriteUtil.generateSprite(buttons[0 + 1], x, y, false);
		set(button, x, y);
	}

	@Override
	public void touchDown() {
		set(buttonHover);
	}

	@Override
	public void touchUp() {
		set(button);
	}

	@Override
	public void touchDownOut() {
	}

	@Override
	public void touchUpOut() {
		set(button);
	}
	
}
