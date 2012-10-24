package fr.gravity.pangolin.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;

import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.screen.AbstractScreen;
import fr.gravity.pangolin.util.CountDown;
import fr.gravity.pangolin.util.GameUtil;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class Controller {

	private GravityPangolinWorld pangolinWorld;
	private Pangolin pangolin;

	static Map<Integer, Boolean> keys = new HashMap<Integer, Boolean>();

	public Controller(GravityPangolinWorld world, Pangolin pangolin) {
		this.pangolinWorld = world;
		this.pangolin = pangolin;
		initKeys();
	}

	// ** Key presses and touches **************** //

	public void initKeys() {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.SPACE, false);
	}

	public void keyDown(int keycode) {
		if (keys.containsKey(keycode))
			keys.get(keys.put(keycode, true));
	}

	public void keyUp(int keycode) {
		if (keycode == Keys.BACKSPACE) {
			AbstractScreen scr = ((AbstractScreen) GameUtil.getScreen());
			if (scr != null)
				scr.switchDebug();
		} else if (keycode == Keys.PLUS)
			GravityPangolinGame.getInstance().nextStage();
		else if (keycode == Keys.MINUS)
			GravityPangolinGame.getInstance().previousStage();

		if (keys.containsKey(keycode))
			keys.get(keys.put(keycode, false));
	}

	/** The main update method **/
	public void update(float delta) {
		processInput();
	}

	/**
	 * Avoid multiple key touch for gravity inversion
	 */
	private CountDown gravityInvertionCountDown = new CountDown(500);

	/** Change Pangolin's state and parameters based on input controls **/
	private void processInput() {

		if (pangolin == null)
			return;

		if (pangolin.isLanded()) {
			if (keys.get(Keys.SPACE)) {
				if (!gravityInvertionCountDown.isFinished())
					return;

				gravityInvertionCountDown.start();
				pangolinWorld.invertGravity();
			} else
				pangolin.idle();
		}

		Direction gravityDirection = pangolinWorld.getGravity().direction;
		if (keys.get(Keys.LEFT) && gravityDirection != Direction.RIGHT && gravityDirection != Direction.LEFT) {
			pangolin.go(Direction.LEFT);
		}
		if (keys.get(Keys.RIGHT) && gravityDirection != Direction.LEFT && gravityDirection != Direction.RIGHT) {
			pangolin.go(Direction.RIGHT);
		}
		if (keys.get(Keys.UP) && gravityDirection != Direction.DOWN && gravityDirection != Direction.UP) {
			pangolin.go(Direction.UP);
		}
		if (keys.get(Keys.DOWN) && gravityDirection != Direction.UP && gravityDirection != Direction.DOWN) {
			pangolin.go(Direction.DOWN);
		}

	}
}
