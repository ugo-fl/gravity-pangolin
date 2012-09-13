package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.gravity.pangolin.entity.Entity;

public class ExitBlock extends Entity {

	public enum ExitSide {
		EXIT_DOWN, EXIT_LEFT, EXIT_UP, EXIT_RIGHT
	}
	
	public ExitBlock(float x, float y, ExitSide exitSide) {
		entityGraphic = new ExitBlockGraphic(x, y, exitSide);
	}

	@Override
	public boolean collides() {
		return false;
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		entityGraphic.draw(spriteBatch);
	}

}
