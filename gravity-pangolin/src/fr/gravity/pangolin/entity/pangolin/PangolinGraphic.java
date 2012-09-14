package fr.gravity.pangolin.entity.pangolin;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.Gravity.Side;
import fr.gravity.pangolin.PangolinWorld;
import fr.gravity.pangolin.entity.EntityGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;

public abstract class PangolinGraphic extends EntityGraphic {
	
	private static final int FRAME_COLS = 2;
	private static final int FRAME_ROWS = 1;
	
	protected Pangolin pangolin;
	
	private Texture pangolinTexture = new Texture("images/sprite_pangolin.png");
	protected HashMap<Side , HashMap<Direction, Animation>> animationMap = new HashMap<Side , HashMap<Direction, Animation>>();
	
	protected float stateTime = 0;
	
	public PangolinGraphic(Pangolin pangolin) {
		this.pangolin = pangolin;
		loadAnimation();
	}
	
	private void loadAnimation() {
		loadFloorAnimations();
		loadCeilingAnimations();
		loadLeftWallAnimations();
		loadRightWallAnimations();
	}
	
	private void loadFloorAnimations() {
		HashMap<Direction, Animation> map = new HashMap<Direction, Animation>();
		
		map.put(Direction.LEFT, new Animation(0.25f, getSprites(true, true, false, false)));
		map.put(Direction.RIGHT, new Animation(0.25f, getSprites(false, true, false, false)));
		animationMap.put(Side.DOWN, map);
	}
	
	private void loadCeilingAnimations() {
		HashMap<Direction, Animation> map = new HashMap<Direction, Animation>();
		
		map.put(Direction.LEFT, new Animation(0.25f, getSprites(true, false, false, false)));
		map.put(Direction.RIGHT, new Animation(0.25f, getSprites(false, false, false, false)));
		animationMap.put(Side.UP, map);
	}

	private void loadLeftWallAnimations() {
		HashMap<Direction, Animation> map = new HashMap<Direction, Animation>();
		
		map.put(Direction.UP, new Animation(0.25f, getSprites(true, true, true, true)));
		map.put(Direction.DOWN, new Animation(0.25f, getSprites(false, true, true, true)));
		animationMap.put(Side.LEFT, map);
	}
	
	private void loadRightWallAnimations() {
		HashMap<Direction, Animation> map = new HashMap<Direction, Animation>();
		
		map.put(Direction.UP, new Animation(0.25f, getSprites(false, true, true, false)));
		map.put(Direction.DOWN, new Animation(0.25f, getSprites(true, true, true, false)));
		animationMap.put(Side.RIGHT, map);
	}	
	
	private Sprite[] getSprites(boolean flipX, boolean flipY, boolean doRotate, boolean rotateClockWise) {
		TextureRegion[][] tmp = TextureRegion.split(pangolinTexture, pangolinTexture.getWidth() / FRAME_COLS,
				pangolinTexture.getHeight() / FRAME_ROWS);
		Sprite[] walkFrames = new Sprite[FRAME_COLS * FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < FRAME_ROWS; i++) {
			for (int j = 0; j < FRAME_COLS; j++) {
				Sprite sprite = new Sprite(tmp[i][j]);
				// Flip and rotate
				sprite.flip(flipX, flipY);
				if (doRotate)
					sprite.rotate90(rotateClockWise);
				walkFrames[index++] = sprite;
			}
		}
		return walkFrames;
	}
	
	public TextureRegion getFrame(float stateTime) {
		Side side = PangolinWorld.getInstance().getGravity().getSide();
		Animation animation = animationMap.get(side).get(pangolin.getDirection());
		return animation.getKeyFrame(stateTime, true);
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		process();
		super.draw(spriteBatch);
	}
	
	public abstract void process();
	
//	@Override
//	public void draw(SpriteBatch spriteBatch) {
//		if (this instanceof IdlePangolinSprite)
//			stateTime = 0;
//		else
//			stateTime += Gdx.graphics.getDeltaTime();
//		setRegion(getFrame(stateTime));
//		super.draw(spriteBatch);
//	}
}
