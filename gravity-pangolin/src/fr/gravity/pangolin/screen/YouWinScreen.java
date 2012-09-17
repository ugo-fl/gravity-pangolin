package fr.gravity.pangolin.screen;

import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;

public class YouWinScreen extends ScreenAbstract {

	private static final long DISPLAY_PERIOD = 3000;
	private long timestamp;
	
	private Sprite background;

	public YouWinScreen() {
		super();
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
		
		loadBackgroundSprite();
		timestamp = new Date().getTime();
	}
	
	private void loadBackgroundSprite() {
		background = TextureLoader.getInstance().getSingleSprite(TextureId.YOU_WIN);
		background.setSize(width, height);
		background.setPosition(-width / 2, -height / 2);
	}
	
	@Override
	public void render(float delta) {
		
		if (new Date().getTime() - timestamp > DISPLAY_PERIOD)
			GravityPangolinGame.getInstance().startNewGame();
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.begin();
		// Draw background
		background.draw(spriteBatch);
		spriteBatch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
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

}
