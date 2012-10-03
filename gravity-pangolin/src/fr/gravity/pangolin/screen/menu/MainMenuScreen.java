package fr.gravity.pangolin.screen.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import fr.gravity.pangolin.game.GravityPangolinGame;

public class MainMenuScreen extends MenuScreen {
//	private static final int HERBS_FRAME_COLS = 2;
//	private static final int HERBS_FRAME_ROWS = 1;
//
//	private static final int MAX_HERBS = 10;
//
//	private TweenManager tweenManager = new TweenManager();

//	private Sprite[] herbSprites = new Sprite[MAX_HERBS];
	private TextButton startButton;
	
	public MainMenuScreen(GravityPangolinGame gravityPangolinGame) {
		super(gravityPangolinGame, null);
	}

	@Override
	public void show() {
		super.show();
//		loadHerbs();
		loadButtons();

//		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
//		Tween.call(windCallback).start(tweenManager);
	}

//	private void loadHerbs() {
//		Texture herbsTexture = new Texture("images/herbs_sprite.png");
//		TextureRegion[][] tmp = TextureRegion.split(herbsTexture,
//				herbsTexture.getWidth() / HERBS_FRAME_COLS,
//				herbsTexture.getHeight() / HERBS_FRAME_ROWS);
//		TextureRegion[] frames = new TextureRegion[HERBS_FRAME_COLS
//				* HERBS_FRAME_ROWS];
//		int index = 0;
//		for (int i = 0; i < HERBS_FRAME_ROWS; i++) {
//			for (int j = 0; j < HERBS_FRAME_COLS; j++) {
//				TextureRegion tr = tmp[i][j];
//				frames[index++] = tr;
//			}
//		}
//
//		// Herbs on the floor
//		herbSprites[3] = SpriteUtil.generateSprite(frames[1], 20, 0, false);
//		herbSprites[4] = SpriteUtil.generateSprite(frames[0], 70, 0, false);
//		herbSprites[5] = SpriteUtil.generateSprite(frames[1], 110, 0, false);
//
//		herbSprites[0] = SpriteUtil.generateSprite(frames[0], 200, 0, false);
//		herbSprites[1] = SpriteUtil.generateSprite(frames[1], 250, 0, false);
//		herbSprites[2] = SpriteUtil.generateSprite(frames[0], 320, 0, false);
//		
//		// Herbs on the left
//		herbSprites[6] = SpriteUtil.generateSprite(frames[0], 0, 160, true);
//		herbSprites[7] = SpriteUtil.generateSprite(frames[1], 0, 100, true);
//
//	}

	private void loadButtons() {
		startButton = new TextButton("Go !", getSkin());
		startButton.x = 100;
		startButton.y = 200;

		startButton.setClickListener(new ClickListener() {
			@Override
			public void click(Actor actor, float x, float y) {
				GravityPangolinGame.getInstance().showSelectPackScreen();
			}
		});
		stage.addActor(startButton);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
//		tweenManager.update(Gdx.graphics.getDeltaTime());

//		SpriteBatch batch = stage.getSpriteBatch();
//		batch.begin();
//		for (Sprite sprite : herbSprites)
//			if (sprite != null)
//				sprite.draw(batch);
//		batch.end();

	}

//	private final TweenCallback windCallback = new TweenCallback() {
//		@Override
//		public void onEvent(int type, BaseTween<?> source) {
//			float d = MathUtils.random() * 0.5f + 0.5f;
//			float t = -0.5f * herbSprites[0].getHeight();
//
//			Timeline.createParallel()
//					.push(Tween.to(herbSprites[0], SpriteAccessor.SKEW_X2X3, d)
//							.target(t, t).ease(Sine.INOUT).repeatYoyo(1, 0)
//							.setCallback(windCallback))
//					.push(Tween.to(herbSprites[1], SpriteAccessor.SKEW_X2X3, d)
//							.target(t, t).ease(Sine.INOUT).delay(d / 3)
//							.repeatYoyo(1, 0))
//					.push(Tween.to(herbSprites[2], SpriteAccessor.SKEW_X2X3, d)
//							.target(t, t).ease(Sine.INOUT).delay(d / 3 * 2)
//							.repeatYoyo(1, 0))
//					.push(Tween.to(herbSprites[3], SpriteAccessor.SKEW_X2X3, d)
//							.target(t, t).ease(Sine.INOUT).delay(d / 3 * 3)
//							.repeatYoyo(1, 0))
//					.push(Tween.to(herbSprites[4], SpriteAccessor.SKEW_X2X3, d)
//							.target(t, t).ease(Sine.INOUT).delay(d / 3 * 4)
//							.repeatYoyo(1, 0))
//					.push(Tween.to(herbSprites[5], SpriteAccessor.SKEW_X2X3, d)
//							.target(t, t).ease(Sine.INOUT).delay(d / 3 * 5)
//							.repeatYoyo(1, 0))
//					.push(Tween.to(herbSprites[6], SpriteAccessor.SKEW_Y2Y3, d)
//							.target(t, t).ease(Sine.INOUT).delay(d / 3 * 3)
//							.repeatYoyo(1, 0))
//					.push(Tween.to(herbSprites[7], SpriteAccessor.SKEW_Y2Y3, d)
//							.target(t, t).ease(Sine.INOUT).delay(d / 3 * 7)
//							.repeatYoyo(1, 0)).start(tweenManager);
//		}
//	};

}
