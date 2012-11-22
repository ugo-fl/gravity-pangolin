package fr.gravity.pangolin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Skin.TintedDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.menu.ButtonsSlider;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.util.GameUtil;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class MapEditorScreen extends GravityPangolinScreen {

	private Stage menuStage;
	
	private Table wrapper;
	
	private Window menuWindow;
	private Table menuTable;
	
	private TextButton menuButton;
	
	public MapEditorScreen(GravityPangolinGame gravityPangolinGame, GravityPangolinWorld pangolinWorld) {
		super(gravityPangolinGame, pangolinWorld);
		
//		int width = pangolinWorld.getSizeX();
//		int height = pangolinWorld.getSizeY();
//		
//		camera = new OrthographicCamera(width, height);
//		camera.setToOrtho(false, width + 1, height + 1);
//		
//		stage = new Stage(width, height, true);
//		stage.setCamera(camera);
	}

	@Override
	public void show() {
		Gdx.app.log(GravityPangolinGame.LOG, "Showing screen: " + getName());
		Gdx.input.setInputProcessor(this);
		
//		((OrthographicCamera) stage.getCamera()).setToOrtho(false, width - 10, height - 10);
//		stage.setCamera(new OrthographicCamera(15, 10));
//		stage.getCamera().translate(1, 1, 0);
		
		menuStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		
		loadMenuTable();
		loadMenuButton();
		
//		Table downBorder = new Table(getSkin());
//		downBorder.setZIndex(999);
//		downBorder.setColor(0, 0, 0, 100);
//		downBorder.center();
//		downBorder.setSize(120, 50);
//		downBorder.debug();
//		
//		menuStage.addActor(downBorder);
	}

	private void loadMenuTable() {
		menuWindow = new Window("Map Editor", getSkin());
		
		menuTable = new Table();
		menuTable.center();
		menuTable.pad(30);
//		menuTable.debug();
		
		TextButton backButton = new TextButton("Back", getSkin());
		backButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				menuWindow.setVisible(false);
				menuButton.setVisible(true);
			}
		});
		TextButton saveButton = new TextButton("Save map", getSkin());
		
		menuTable.add(backButton).padBottom(5).fill();
		menuTable.row();
		menuTable.add(saveButton);
		menuWindow.add(menuTable);
	}
	
	private void loadMenuButton() {
		wrapper = new Table();
		wrapper.setFillParent(true);
		wrapper.pad(10);
//		wrapper.debug();

		menuButton = new TextButton("-", getSkin());
		menuButton.setPosition(wrapper.getWidth(), 1);
		menuButton.addListener(new ClickListener() {

			public void clicked (InputEvent event, float x, float y) {
				menuWindow.setVisible(true);
				menuButton.setVisible(false);
			}
		});

		wrapper.add(menuWindow).expandX().expandY();
		wrapper.add(menuButton).right().bottom();
		menuStage.addActor(wrapper);
		
		// Hide the menu window first
		menuWindow.setVisible(false);
	}

	@Override
	public void render(float delta) {
		stage.draw();
		menuStage.draw();
		Table.drawDebug(menuStage);
	}

	private Entity touchedEntity;

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (menuStage.touchDown(x, y, pointer, button) || menuWindow.isVisible())
			return true;

		for (Entity entity : pangolinWorld.getEntities()) {
			if (entity.touchDown(x, y)) {
				touchedEntity = entity;
				break;
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		if (touchedEntity != null) {
			touchedEntity.setPosition(new Vector2(Math.round(GameUtil.projectCoordinateX(x)), Math.round(GameUtil.projectCoordinateY(y))));
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		menuStage.touchUp(x, y, pointer, button);
		
		touchedEntity = null;
		return false;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.MENU || keycode == Keys.L) {
			Vector3 position = stage.getCamera().position;
			position.set(position.x - 1, position.y - 1, 0);
			stage.getCamera().update();
		}
		return false;
	}

}
