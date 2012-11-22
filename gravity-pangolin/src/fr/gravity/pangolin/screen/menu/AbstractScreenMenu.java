package fr.gravity.pangolin.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.block.ExitBlock;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.screen.IScreen;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public abstract class AbstractScreenMenu implements IScreen {
	// the fixed viewport dimensions (ratio: 1.6)
	public static final int GAME_VIEWPORT_WIDTH = 480, GAME_VIEWPORT_HEIGHT = 320;
	// public static final int MENU_VIEWPORT_WIDTH = 800, MENU_VIEWPORT_HEIGHT =
	// 480;

	protected final GravityPangolinGame game;
	protected final GravityPangolinWorld pangolinWorld;
	protected final Stage stage;

	private BitmapFont font;
	private SpriteBatch batch;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;

	protected int width;
	protected int height;

	public AbstractScreenMenu(GravityPangolinGame game, GravityPangolinWorld pangolinWorld) {
		this.game = game;
		this.pangolinWorld = pangolinWorld;
		width = GAME_VIEWPORT_WIDTH;
		height = GAME_VIEWPORT_HEIGHT;
		// width = (isGameScreen() ? GAME_VIEWPORT_WIDTH : MENU_VIEWPORT_WIDTH);
		// height = (isGameScreen() ? GAME_VIEWPORT_HEIGHT :
		// MENU_VIEWPORT_HEIGHT);
		this.stage = new Stage(width, height, true);
		// set the stage as the input processor
		Gdx.input.setInputProcessor(stage);
	}

	protected String getName() {
		return getClass().getSimpleName();
	}

	protected boolean isGameScreen() {
		return false;
	}

	// Lazily loaded collaborators

	public BitmapFont getFont() {
		if (font == null) {
			font = new BitmapFont();
		}
		return font;
	}

	// public SpriteBatch getBatch() {
	// if (batch == null) {
	// batch = new SpriteBatch();
	// }
	// return batch;
	// }

	public Skin getSkin() {
		// Disabled because does not seem to work when hiding/showing a screen
		// if (skin == null) {
		FileHandle skinFile = Gdx.files.internal("skin/uiskin.json");
		skin = new Skin(skinFile);
		// }
		return skin;
	}

	protected Table getTable() {
		if (table == null) {
			table = new Table(getSkin());
			table.setFillParent(true);
			stage.addActor(table);
		}
		return table;
	}

	protected void loadBackButton(ClickListener clickListener) {
		TextButton backButton = new TextButton("Back", getSkin());
		backButton.setX(400);
		backButton.setY(height - 60);
		backButton.setWidth(50);
		backButton.setHeight(50);
		backButton.addListener(clickListener);
		stage.addActor(backButton);
	}

	// Screen implementation

	@Override
	public void show() {
		Gdx.app.log(GravityPangolinGame.LOG, "Showing screen: " + getName());
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(GravityPangolinGame.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height);
	}

	@Override
	public void render(float delta) {
		// (1) process the game logic

		// update the actors
		stage.act(delta);

		// (2) draw the result

		// clear the screen with the given RGB color (black)
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// draw the actors
		stage.draw();
		drawDebugView();

		// draw the table debug lines
		Table.drawDebug(stage);
	}

	private void drawDebugView() {
		ShapeRenderer debugRenderer = new ShapeRenderer();

		debugRenderer.setProjectionMatrix(stage.getCamera().combined);

		if (pangolinWorld == null)
			return;

		Pangolin pangolin = pangolinWorld.getPangolin();

		for (Actor actor : stage.getActors()) {
			if (!(actor instanceof Entity))
				continue;

			Rectangle bounds = ((Entity) actor).getBoundingRectangle();

			// Draws actors
			if (actor instanceof ExitBlock) {
				// BoundingBox bb = ((ExitBlock)
				// actor).getEntityGraphic().getBoundingBox();
				// debugRenderer.begin(ShapeType.Line);
			} else {
				debugRenderer.begin(ShapeType.Rectangle);
				debugRenderer.setColor(new Color(0, 1, 0, 1));
				debugRenderer.rect(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
				debugRenderer.end();
			}

			// Draws collision points
			debugRenderer.begin(ShapeType.Circle);
			debugRenderer.setColor(new Color(1, 0, 0, 0));
			debugRenderer.circle(bounds.getX(), bounds.getY(), 2.5F);
			debugRenderer.setColor(new Color(25, 85, 26, 3));
			debugRenderer.circle(bounds.getX(), bounds.getY() + bounds.getHeight(), 2.5F);
			debugRenderer.circle(bounds.getX(), bounds.getY() - pangolin.getHeight(), 2.5F);
			debugRenderer.setColor(new Color(1, 1, 0, 1));
			debugRenderer.circle(bounds.getX() + bounds.getWidth(), bounds.getY(), 2.5F);
			debugRenderer.circle(bounds.getX() - pangolin.getWidth(), bounds.getY(), 2.5F);
			debugRenderer.end();
		}
	}

	@Override
	public void hide() {
		Gdx.app.log(GravityPangolinGame.LOG, "Hiding screen: " + getName());

		// dispose the screen when leaving the screen;
		// note that the dipose() method is not called automatically by the
		// framework, so we must figure out when it's appropriate to call it
		dispose();
	}

	@Override
	public void pause() {
		Gdx.app.log(GravityPangolinGame.LOG, "Pausing screen: " + getName());
	}

	@Override
	public void resume() {
		Gdx.app.log(GravityPangolinGame.LOG, "Resuming screen: " + getName());
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void dispose() {
		Gdx.app.log(GravityPangolinGame.LOG, "Disposing screen: " + getName());

		// the following call disposes the screen's stage, but on my computer it
		// crashes the game so I commented it out; more info can be found at:
		// http://www.badlogicgames.com/forum/viewtopic.php?f=11&t=3624
		// stage.dispose();

		// as the collaborators are lazily loaded, they may be null
		if (font != null)
			font.dispose();
		// if (batch != null)
		// batch.dispose();
		if (skin != null)
			skin.dispose();
		if (atlas != null)
			atlas.dispose();
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getPpuX() {
		if (pangolinWorld == null)
			return 1;
		float sizeX = pangolinWorld.getSizeX();
		return width / sizeX;
	}

	public float getPpuY() {
		if (pangolinWorld == null)
			return 1;
		float sizeY = pangolinWorld.getSizeY();
		return height / sizeY;
	}

}