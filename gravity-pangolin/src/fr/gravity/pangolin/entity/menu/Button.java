package fr.gravity.pangolin.entity.menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.ButtonGraphic;

public class Button extends Entity {

	public Button(float x, float y) {
		entityGraphic = new ButtonGraphic(x, y);
	}

	@Override
	public void touchDown() {
	}

	@Override
	public void touchUp() {
		GravityPangolinGame.getInstance().showSelectLevelScreen();
	}
	
//	public void draw(SpriteBatch spriteBatch) {
//		entityGraphic.draw(spriteBatch);
//	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		entityGraphic.draw(batch);
	}

	@Override
	public Actor hit(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

}
