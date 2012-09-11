package fr.gravity.pangolin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ScreenAbstract implements Screen, InputProcessor {
	
	protected float width;
	protected float height;

	protected float ppuX;
	protected float ppuY;
	
	protected OrthographicCamera camera;
	protected SpriteBatch spriteBatch;
	
	public ScreenAbstract() {
		float screenW = Gdx.graphics.getWidth();
		float screenH = Gdx.graphics.getHeight();
		width = 100;
		height = width * screenH / screenW;

		ppuX = screenW / width;
		ppuY = screenH / height;

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
