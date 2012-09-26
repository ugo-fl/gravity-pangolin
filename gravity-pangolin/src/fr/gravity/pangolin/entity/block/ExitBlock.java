package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.CollisionHelper;
import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.PangolinWorld;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.ExitBlockGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;

public class ExitBlock extends Entity {

	// public enum ExitSide {
	// EXIT_DOWN, EXIT_LEFT, EXIT_UP, EXIT_RIGHT
	// }

	private PangolinWorld pangolinWorld;
	private Pangolin pangolin;
	private Direction direction;

	public ExitBlock(float x, float y, Direction direction) {
		entityGraphic = new ExitBlockGraphic(x, y, direction);
		this.direction = direction;
		pangolinWorld = GravityPangolinGame.getInstance().getPangolinWorld();
		pangolin = pangolinWorld.getPangolin();
	}

	// @Override
	// public void draw(SpriteBatch spriteBatch) {
	// entityGraphic.draw(spriteBatch);
	// }

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
		return null;
	}

	@Override
	public Entity collides() {
		if (!pangolin.isLanded())
			return null;
		Direction gravityDirection = pangolinWorld.getGravity().getDirection();
//		if (CollisionHelper.collides(pangolin, this, direction))
		if (gravityDirection == direction)
			GravityPangolinGame.getInstance().youWin();
		return this;
	}
}
