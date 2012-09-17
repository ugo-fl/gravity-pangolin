package fr.gravity.pangolin.screen;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Sine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.entity.menu.Button;
import fr.gravity.pangolin.tween.SpriteAccessor;
import fr.gravity.pangolin.util.SpriteUtil;

public class MainMenuScreen extends ScreenAbstract {

//	public static final float MAIN_MENU_SCR_WIDTH = 100;
//	public static final float MAIN_MENU_SCR_HEIGHT = MAIN_MENU_SCR_WIDTH * Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
	
	private static final int HERBS_FRAME_COLS = 2;
	private static final int HERBS_FRAME_ROWS = 1;

	private static final int MAX_HERBS = 10;

	private TweenManager tweenManager = new TweenManager();

	private Sprite background;
	private Sprite[] herbSprites = new Sprite[MAX_HERBS];

	private Button newGameButton;

	public MainMenuScreen() {
		super();
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		loadBackgroundSprite();
		loadHerbSprites();
		newGameButton = new Button(5, 40);

		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		Tween.call(windCallback).start(tweenManager);
	}

	private void loadBackgroundSprite() {
		background = new Sprite(new Texture("images/splash.png"));
		background.setSize(width, height);
		background.setPosition(-width / 2, -height / 2);
	}

	private void loadHerbSprites() {
		Texture herbsTexture = new Texture("images/herbs_sprite.png");
		TextureRegion[][] tmp = TextureRegion.split(herbsTexture, herbsTexture.getWidth() / HERBS_FRAME_COLS,
				herbsTexture.getHeight() / HERBS_FRAME_ROWS);
		TextureRegion[] frames = new TextureRegion[HERBS_FRAME_COLS * HERBS_FRAME_ROWS];
		int index = 0;
		for (int i = 0; i < HERBS_FRAME_ROWS; i++) {
			for (int j = 0; j < HERBS_FRAME_COLS; j++) {
				TextureRegion tr = tmp[i][j];
				frames[index++] = tr;
			}
		}

		// Herbs on the floor
		herbSprites[0] = SpriteUtil.generateSprite(frames[0], 15, 0, false);
		herbSprites[1] = SpriteUtil.generateSprite(frames[1], 25, 0, false);
		herbSprites[2] = SpriteUtil.generateSprite(frames[0], 35, 0, false);

		herbSprites[3] = SpriteUtil.generateSprite(frames[1], 65, 0, false);
		herbSprites[4] = SpriteUtil.generateSprite(frames[0], 75, 0, false);
		herbSprites[5] = SpriteUtil.generateSprite(frames[1], 85, 0, false);
		
		// Herbs on the left
		herbSprites[6] = SpriteUtil.generateSprite(frames[0], 0, 25, true);
		herbSprites[7] = SpriteUtil.generateSprite(frames[1], 0, 15, true);
	}

	@Override
	public void render(float delta) {
		tweenManager.update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		// Draw background
		background.draw(spriteBatch);
		// Draw Buttons
		newGameButton.draw(spriteBatch);
		// Draw herbs
		for (Sprite sprite : herbSprites)
			if (sprite != null)
				sprite.draw(spriteBatch);
		spriteBatch.end();
	}

	private final TweenCallback windCallback = new TweenCallback() {
		@Override
		public void onEvent(int type, BaseTween<?> source) {
			float d = MathUtils.random() * 0.5f + 0.5f;
			float t = -0.5f * herbSprites[0].getHeight();

			Timeline.createParallel()
					.push(Tween.to(herbSprites[0], SpriteAccessor.SKEW_X2X3, d).target(t, t).ease(Sine.INOUT)
							.repeatYoyo(1, 0).setCallback(windCallback))
					.push(Tween.to(herbSprites[1], SpriteAccessor.SKEW_X2X3, d).target(t, t).ease(Sine.INOUT)
							.delay(d / 3).repeatYoyo(1, 0))
					.push(Tween.to(herbSprites[2], SpriteAccessor.SKEW_X2X3, d).target(t, t).ease(Sine.INOUT)
							.delay(d / 3 * 2).repeatYoyo(1, 0))
					.push(Tween.to(herbSprites[3], SpriteAccessor.SKEW_X2X3, d).target(t, t).ease(Sine.INOUT)
							.delay(d / 3 * 3).repeatYoyo(1, 0))
					.push(Tween.to(herbSprites[4], SpriteAccessor.SKEW_X2X3, d).target(t, t).ease(Sine.INOUT)
							.delay(d / 3 * 4).repeatYoyo(1, 0))
					.push(Tween.to(herbSprites[5], SpriteAccessor.SKEW_X2X3, d).target(t, t).ease(Sine.INOUT)
							.delay(d / 3 * 5).repeatYoyo(1, 0))
					.push(Tween.to(herbSprites[6], SpriteAccessor.SKEW_Y2Y3, d).target(t, t).ease(Sine.INOUT)
							.delay(d / 3 * 3).repeatYoyo(1, 0))
					.push(Tween.to(herbSprites[7], SpriteAccessor.SKEW_Y2Y3, d).target(t, t).ease(Sine.INOUT)
							.delay(d / 3 * 7).repeatYoyo(1, 0)).start(tweenManager);
		}
	};

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		newGameButton.touchDown(x, y);
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		newGameButton.touchUp(x, y);
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
