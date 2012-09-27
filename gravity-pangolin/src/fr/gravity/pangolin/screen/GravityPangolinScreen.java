package fr.gravity.pangolin.screen;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import fr.gravity.pangolin.Controller;
import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.PangolinWorld;
import fr.gravity.pangolin.PangolinWorldRenderer;
import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;

public class GravityPangolinScreen extends AbstractScreen {

	private PangolinWorld pangolinWorld;
	private PangolinWorldRenderer worldRenderer;

	public GravityPangolinScreen(GravityPangolinGame gravityPangolinGame, PangolinWorld pangolinWorld) {
		super(gravityPangolinGame, pangolinWorld);
		this.pangolinWorld = pangolinWorld;
	}

	// public GravityPangolinScreen(PangolinWorld pangolinWorld) {
	// super(pangolinWorld.getSizeX(), pangolinWorld.getSizeY());
	// this.pangolinWorld = pangolinWorld;
	// }

	@Override
	public void show() {
//		worldRenderer = new PangolinWorldRenderer(pangolinWorld, this, true);

//		pangolinWorld = PangolinWorld.getInstance(mapFile);
		super.show();
		loadBackground();
		pangolinWorld.init(stage);
		stage.setKeyboardFocus(pangolinWorld.getPangolin());
	}

	// public void init() {
	// worldRenderer = new PangolinWorldRenderer(pangolinWorld, this, true);
	// controller = new Controller(pangolinWorld);
	//
	// loadBackground();
	// stage.addActor(pangolinWorld.getPangolin());
	// }

	private void loadBackground() {
		Image background = new Image(TextureLoader.getInstance().getTextureRegions(TextureId.BACKGROUND)[0]);
		background.setFillParent(true);
		stage.addActor(background);
	}

//	 @Override
//	 public void show() {
//	 Gdx.input.setInputProcessor(this);
//	 }
//
//	 @Override
//	 public void render(float delta) {
//	 Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
//	 Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
//	
//	 controller.update(delta);
//	 worldRenderer.render();
//	 }
//
//	@Override
//	 public boolean keyDown(int keycode) {
//	 if (keycode == Keys.LEFT)
//	 controller.leftPressed();
//	 if (keycode == Keys.RIGHT)
//	 controller.rightPressed();
//	 if (keycode == Keys.UP)
//	 controller.upPressed();
//	 if (keycode == Keys.DOWN)
//	 controller.downPressed();
//	 if (keycode == Keys.SPACE)
//	 controller.gravityChangePressed();
//	 return true;
//	 }
//
//	 @Override
//	 public boolean keyUp(int keycode) {
//	 if (keycode == Keys.LEFT)
//	 controller.leftReleased();
//	 if (keycode == Keys.RIGHT)
//	 controller.rightReleased();
//	 if (keycode == Keys.UP)
//	 controller.upReleased();
//	 if (keycode == Keys.DOWN)
//	 controller.downReleased();
//	 if (keycode == Keys.SPACE)
//	 controller.gravityChangeReleased();
//	 return true;
//	 }
//
//	 @Override
//	public boolean keyTyped(char character) {
//	 // TODO Auto-generated method stub
//	 return false;
//	 }
//
//	 @Override
//	 public boolean touchDown(int x, int y, int pointer, int button) {
//	 if (!Gdx.app.getType().equals(ApplicationType.Android))
//	 return false;
//	 if (x < width / 2 && y > height / 2) {
//	 controller.leftPressed();
//	 }
//	 if (x > width / 2 && y > height / 2) {
//	 controller.rightPressed();
//	 }
//	 return true;
//	 }
//
//	 @Override
//	 public boolean touchUp(int x, int y, int pointer, int button) {
//	 if (!Gdx.app.getType().equals(ApplicationType.Android))
//	 return false;
//	 if (x < width / 2 && y > height / 2) {
//	 controller.leftReleased();
//	 }
//	 if (x > width / 2 && y > height / 2) {
//	 controller.rightReleased();
//	 }
//	 return true;
//	 }

//	 @Override
//	 public boolean touchDragged(int x, int y, int pointer) {
//	 // TODO Auto-generated method stub
//	 return false;
//	 }
	//
	// @Override
	// public boolean touchMoved(int x, int y) {
	// // TODO Auto-generated method stub
	// return false;
	// }
	//
	// @Override
	// public boolean scrolled(int amount) {
	// // TODO Auto-generated method stub
	// return false;
	// }
	//
	// @Override
	// public void resize(int width, int height) {
	// // worldRenderer.setSize(width, height);
	// // this.width = width;
	// // this.height = height;
	// }
	//
	// @Override
	// public void hide() {
	// // Gdx.input.setInputProcessor(null);
	// }
	//
	// @Override
	// public void pause() {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void resume() {
	// }
	//
	// @Override
	// public void dispose() {
	// // Gdx.input.setInputProcessor(null);
	// }

}
