package fr.gravity.pangolin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

public abstract class AbstractScreen implements Screen, InputProcessor {
	
//	protected static float SCREEN_WIDTH = 480;
//	protected static float SCREEN_HEIGHT = 320;
	
	protected static float DEFAULT_WIDTH = 100;
	protected static float DEFAULT_HEIGHT = DEFAULT_WIDTH * Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
	
	protected float width;
	protected float height;

	protected float ppuX;
	protected float ppuY;
	
	protected OrthographicCamera camera;
	protected SpriteBatch spriteBatch;
	
	/* MENU AND BUTTONS */
	private Skin skin;
	private Table table;
	
	protected AbstractScreen() {
		this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	protected AbstractScreen(float width, float height) {
		float screenW = Gdx.graphics.getWidth();
		float screenH = Gdx.graphics.getHeight();
		this.width = width;
		this.height = height;

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

	protected Skin getSkin()
    {
        if( skin == null ) {
            FileHandle skinFile = Gdx.files.internal( "skin/uiskin.json" );
            skin = new Skin( skinFile );
        }
        return skin;
    }
	
	protected Table getTable()
    {
        if( table == null ) {
            table = new Table( getSkin() );
            table.setFillParent( true );
//            if( Tyrian.DEV_MODE ) {
//                table.debug();
//            }
        }
        return table;
    }
}
