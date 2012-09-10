package fr.gravity.pangolin.entity;


public class WalkingPangolinSprite extends PangolinSprite {

//	private Texture pangolinTexture = new Texture("images/sprite_pangolin.png");

//	private HashMap<Side , HashMap<Direction, Animation>> animationMap = new HashMap<Side , HashMap<Direction, Animation>>();
	
//	private Animation walkAnimationFloorLeft;
//	private Animation walkAnimationFloorRight;
//
//	private Animation walkAnimationCeilingLeft;
//	private Animation walkAnimationCeilingRight;
//
//	private Animation walkAnimationLeftWallUp;
//	private Animation walkAnimationLeftWallDown;
//
//	private Animation walkAnimationRightWallUp;
//	private Animation walkAnimationRightWallDown;
//
//	private Animation currentWalkAnimation;

	public WalkingPangolinSprite(Pangolin pangolin) {
		super(pangolin);
//		loadAnimation();
	}

//	private void loadAnimation() {
//
//		loadFloorAnimations();
//		loadCeilingAnimations();
//		loadLeftWallAnimations();
//		loadRightWallAnimations();
//	}
//	
//	private void loadFloorAnimations() {
//		HashMap<Direction, Animation> map = new HashMap<Direction, Animation>();
//		
//		map.put(Direction.LEFT, new Animation(0.25f, getSprites(true, true, false, false)));
//		map.put(Direction.RIGHT, new Animation(0.25f, getSprites(false, true, false, false)));
//		animationMap.put(Side.DOWN, map);
//	}
//	
//	private void loadCeilingAnimations() {
//		HashMap<Direction, Animation> map = new HashMap<Direction, Animation>();
//		
//		map.put(Direction.LEFT, new Animation(0.25f, getSprites(true, false, false, false)));
//		map.put(Direction.RIGHT, new Animation(0.25f, getSprites(false, false, false, false)));
//		animationMap.put(Side.UP, map);
//	}
//
//	private void loadLeftWallAnimations() {
//		HashMap<Direction, Animation> map = new HashMap<Direction, Animation>();
//		
//		map.put(Direction.UP, new Animation(0.25f, getSprites(true, true, true, true)));
//		map.put(Direction.DOWN, new Animation(0.25f, getSprites(false, true, true, true)));
//		animationMap.put(Side.LEFT, map);
//	}
//	
//	private void loadRightWallAnimations() {
//		HashMap<Direction, Animation> map = new HashMap<Direction, Animation>();
//		
//		map.put(Direction.UP, new Animation(0.25f, getSprites(false, true, true, false)));
//		map.put(Direction.DOWN, new Animation(0.25f, getSprites(true, true, true, false)));
//		animationMap.put(Side.RIGHT, map);
//	}	
//	
//	private Sprite[] getSprites(boolean flipX, boolean flipY, boolean doRotate, boolean rotateClockWise) {
//		TextureRegion[][] tmp = TextureRegion.split(pangolinTexture, pangolinTexture.getWidth() / FRAME_COLS,
//				pangolinTexture.getHeight() / FRAME_ROWS);
//		Sprite[] walkFrames = new Sprite[FRAME_COLS * FRAME_ROWS];
//		int index = 0;
//		for (int i = 0; i < FRAME_ROWS; i++) {
//			for (int j = 0; j < FRAME_COLS; j++) {
//				Sprite sprite = new Sprite(tmp[i][j]);
//				// Flip and rotate
//				sprite.flip(flipX, flipY);
//				if (doRotate)
//					sprite.rotate90(rotateClockWise);
//				walkFrames[index++] = sprite;
//			}
//		}
//		return walkFrames;
//	}

}
