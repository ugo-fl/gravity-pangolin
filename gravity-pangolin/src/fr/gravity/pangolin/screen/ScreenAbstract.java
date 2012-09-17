package fr.gravity.pangolin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ScreenAbstract implements Screen, InputProcessor {
	
	protected static float SCREEN_WIDTH = 480;
	protected static float SCREEN_HEIGHT = 320;
	
	protected static float DEFAULT_WIDTH = 100;
	protected static float DEFAULT_HEIGHT = DEFAULT_WIDTH * Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
	
	protected float width;
	protected float height;

	protected float ppuX;
	protected float ppuY;
	
	protected OrthographicCamera camera;
	protected SpriteBatch spriteBatch;
	
	protected ScreenAbstract() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	protected ScreenAbstract(float width, float height) {
//		float screenW = Gdx.graphics.getWidth();
//		float screenH = Gdx.graphics.getHeight();
		this.width = width;
		this.height = height;

		ppuX = SCREEN_WIDTH / width;
		ppuY = SCREEN_HEIGHT / height;

		camera = new OrthographicCamera(width, height);
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);

	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getPpuX() {
		return ppuX;
	}

	public float getPpuY() {
		return ppuY;
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

}
