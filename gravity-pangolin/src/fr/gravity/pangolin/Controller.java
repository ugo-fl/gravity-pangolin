package fr.gravity.pangolin;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

import fr.gravity.pangolin.Gravity.Side;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.util.GameUtil;

public class Controller {

	enum Keys {
		LEFT, RIGHT, UP, DOWN, GRAVITY_CHANGE
	}

	private PangolinWorld pangolinWorld;
	private Pangolin pangolin;

	static Map<Keys, Boolean> keys = new HashMap<Controller.Keys, Boolean>();

	public Controller(PangolinWorld world) {
		this.pangolinWorld = world;
		this.pangolin = world.getPangolin();
		initKeys();
	}

	// ** Key presses and touches **************** //

	public void initKeys() {
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.GRAVITY_CHANGE, false);
	}
	
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}

	public void upPressed() {
		keys.get(keys.put(Keys.UP, true));
	}

	public void downPressed() {
		keys.get(keys.put(Keys.DOWN, true));
	}

	public void gravityChangePressed() {
		keys.get(keys.put(Keys.GRAVITY_CHANGE, true));
	}

	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}

	public void upReleased() {
		keys.get(keys.put(Keys.UP, false));
	}

	public void downReleased() {
		keys.get(keys.put(Keys.DOWN, false));
	}
	
	public void gravityChangeReleased() {
		keys.get(keys.put(Keys.GRAVITY_CHANGE, false));
	}

	/** The main update method **/
	public void update(float delta) {
		processInput();
		controlPangolinMovement(delta);
		pangolin.update(delta);
	}

	private void controlPangolinMovement(float delta) {

		if (GameUtil.isOutOfScreen(pangolin.getX(), pangolin.getY()))
			GravityPangolinGame.getInstance().gameOver();

		pangolin.setLanded(false);
		// Control for the X axis
		{
			Vector2 positionCpy = pangolin.getPosition();
			Vector2 velocityCpy = pangolin.getVelocity().cpy();

			velocityCpy.set(pangolin.getVelocity().x, 0);
			pangolin.translate(velocityCpy.tmp().mul(delta));

			Entity collidedEntity = CollisionHelper.collidesAny(pangolin,
					pangolinWorld.getBlocks());
			if (collidedEntity != null)
				if (CollisionHelper.collidesLeft(pangolin, collidedEntity)) {
					pangolin.getVelocity().set(0, pangolin.getVelocity().y);
					if (pangolinWorld.getGravity().getSide() == Side.LEFT)
						pangolin.setLanded(true);
				} else if (CollisionHelper.collidesRight(pangolin,
						collidedEntity)) {
					pangolin.getVelocity().set(0, pangolin.getVelocity().y);
					if (pangolinWorld.getGravity().getSide() == Side.RIGHT)
						pangolin.setLanded(true);
				}
			pangolin.setPosition(positionCpy);
		}
		// Control for the Y axis
		{
			Vector2 positionCpy = pangolin.getPosition().cpy();
			Vector2 velocityCpy = pangolin.getVelocity().cpy();

			velocityCpy.set(0, pangolin.getVelocity().y);
			pangolin.translate(velocityCpy.tmp().mul(delta));

			Entity collidedEntity = CollisionHelper.collidesAny(pangolin,
					pangolinWorld.getBlocks());
			if (collidedEntity != null)
				if (CollisionHelper.collidesDown(pangolin, collidedEntity)) {
					pangolin.getVelocity().set(pangolin.getVelocity().x, 0);
					if (pangolinWorld.getGravity().getSide() == Side.DOWN)
						pangolin.setLanded(true);
				} else if (CollisionHelper.collidesUp(pangolin, collidedEntity)) {
					pangolin.getVelocity().set(pangolin.getVelocity().x, 0);
					if (pangolinWorld.getGravity().getSide() == Side.UP)
						pangolin.setLanded(true);
				}
			pangolin.setPosition(positionCpy);
		}

	}

	/** Change Pangolin's state and parameters based on input controls **/
	private void processInput() {

		if (pangolin == null)
			return;

		pangolin.idle();

		if (keys.get(Keys.GRAVITY_CHANGE) && pangolin.isLanded()) {
			pangolinWorld.getGravity().switchSide();
			// To avoid one frame of pangolin upside down (not falling) when the button is pressed
			pangolin.setLanded(false);
		}
		
		Side gravitySide = pangolinWorld.getGravity().getSide();
		if (keys.get(Keys.LEFT) && gravitySide != Side.RIGHT) {
			pangolin.goLeft();
		} else if (gravitySide == Side.LEFT) {
			pangolin.fallLeft();
		}
		if (keys.get(Keys.RIGHT) && gravitySide != Side.LEFT) {
			pangolin.goRight();
		} else if (gravitySide == Side.RIGHT) {
			pangolin.fallRight();
		}
		if (keys.get(Keys.UP) && gravitySide != Side.DOWN) {
			pangolin.goUp();
		} else if (gravitySide == Side.UP) {
			pangolin.fallUp();
		}
		if (keys.get(Keys.DOWN) && gravitySide != Side.UP) {
			pangolin.goDown();
		} else if (gravitySide == Side.DOWN) {
			pangolin.fallDown();
		}
		
	}
}
