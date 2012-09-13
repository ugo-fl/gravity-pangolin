package fr.gravity.pangolin.menu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;
import fr.gravity.pangolin.util.SpriteUtil;

public class Button {

	private Sprite button;
	private Sprite buttonHover;

	private boolean isHover;

	public Button(int buttonPos, float x, float y) {
		TextureRegion[] buttons = TextureLoader.getInstance().getTextureRegions(TextureId.BUTTONS);
		button = SpriteUtil.generateSprite(buttons[buttonPos], x, y, false);
		buttonHover = SpriteUtil.generateSprite(buttons[buttonPos + 1], x, y, false);
	}

	public void touchDown() {
		isHover = true;
	}

	public void touchUp() {
		isHover = false;
	}

	public Sprite getSprite() {
		return isHover ? buttonHover : button;
	}
	
	public void draw(SpriteBatch spriteBatch) {
		if (isHover)
			buttonHover.draw(spriteBatch);
		else
			button.draw(spriteBatch);
	}

	public boolean isHover() {
		return isHover;
	}

}
