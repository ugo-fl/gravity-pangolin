package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.FadeOut;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.ExitBlockGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.game.CountDown;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.world.PangolinWorld;

public class ExitBlock extends Entity {

	private static final int STOP_PERIOD = 1000;
	private CountDown countDown = new CountDown(STOP_PERIOD);

	private PangolinWorld pangolinWorld;
	private Direction direction = Direction.DOWN;

	public ExitBlock(PangolinWorld pangolinWorld) {
		this.pangolinWorld = pangolinWorld;
	}

	public void init(float x, float y) {
		entityGraphic = new ExitBlockGraphic(x, y, direction);
		addActor(entityGraphic);
	}

	@Override
	public void touchDown() {
	}

	@Override
	public void touchUp() {
	}

//	@Override
//	public void draw(SpriteBatch batch, float parentAlpha) {
//		entityGraphic.draw(batch);
//	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}

	@Override
	public Entity collides() {
		Pangolin pangolin = pangolinWorld.getPangolin();
		Direction gravityDirection = pangolinWorld.getGravity().getDirection();
		if (gravityDirection != direction)
			return null;

		if (!countDown.isStarted()) {
			pangolin.disableController();
			countDown.start();
			return null;
		}
		FadeOut fadeOutAction = FadeOut.$(2f);
		pangolin.action(fadeOutAction);
//		if (!countDown.waitForIt())
//			return null;
		
		GravityPangolinGame.getInstance().youWin();
		return null;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
