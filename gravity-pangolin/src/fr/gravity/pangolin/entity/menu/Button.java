package fr.gravity.pangolin.entity.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.entity.Entity;

public class Button extends Entity {

	public Button(float x, float y) {
		entityGraphic = new ButtonGraphic(x, y);
	}

	@Override
	public void touchDown() {
	}

	@Override
	public void touchUp() {
		GravityPangolinGame.getInstance().startNewGame();
	}
	
	public void draw(SpriteBatch spriteBatch) {
		entityGraphic.draw(spriteBatch);
	}

	@Override
	public boolean collides() {
		return false;
	}

}
