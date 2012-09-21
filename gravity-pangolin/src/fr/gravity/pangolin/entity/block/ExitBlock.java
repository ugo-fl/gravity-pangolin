package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.ExitBlockGraphic;

public class ExitBlock extends Entity {

	public enum ExitSide {
		EXIT_DOWN, EXIT_LEFT, EXIT_UP, EXIT_RIGHT
	}
	
	public ExitBlock(float x, float y, ExitSide exitSide) {
		entityGraphic = new ExitBlockGraphic(x, y, exitSide);
	}

//	@Override
//	public void draw(SpriteBatch spriteBatch) {
//		entityGraphic.draw(spriteBatch);
//	}

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		entityGraphic.draw(batch);
	}

	@Override
	public Actor hit(float x, float y) {
		GravityPangolinGame.getInstance().youWin();
		return this;
	}

}
