package fr.gravity.pangolin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.util.GameUtil;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class MapEditorScreen extends GravityPangolinScreen {

	private Stage menuStage;
	
	private Table wrapper;
	
	private Window menuWindow;
	private Table menuTable;
	
	private Table menuButtonTable;
	private TextButton menuButton;
	
	public MapEditorScreen(GravityPangolinGame gravityPangolinGame, GravityPangolinWorld pangolinWorld) {
		super(gravityPangolinGame, pangolinWorld);
	}

	@Override
	public void show() {
		Gdx.app.log(GravityPangolinGame.LOG, "Showing screen: " + getName());
		Gdx.input.setInputProcessor(this);
		
		menuStage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		
		loadMenuTable();
		loadMenuButton();
	}

	private void loadMenuTable() {
		menuTable = new Table();
		menuTable.setColor(new Color(0, 0, 0, 1));
		menuTable.debugTable();
		menuTable.setZIndex(999);
		menuTable.center();
		
		TextButton backButton = new TextButton("Back", getSkin());
		backButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				wrapper.removeActor(menuWindow);
//				wrapper.right().bottom();
//				wrapper.add(menuButtonTable);
			}
		});
		TextButton saveButton = new TextButton("Save map", getSkin());
		
		menuTable.add(backButton);
		menuTable.row();
		menuTable.add(saveButton);
	}

	private void loadMenuButton() {
		menuWindow = new Window("Menu test", getSkin());
		menuWindow.
		
		wrapper = new Table();
		wrapper.setColor(0, 1, 0, 1);
		wrapper.setFillParent(true);
		wrapper.pad(10);
		wrapper.debugTable();
		wrapper.right().bottom();

		menuButton = new TextButton("-", getSkin());
		menuButton.setPosition(wrapper.getWidth(), 1);
		menuButton.addListener(new ClickListener() {

			public void clicked (InputEvent event, float x, float y) {
//				wrapper.removeActor(menuButtonTable);
//				wrapper.center();
//				wrapper.add(menuTable);
				menuStage.addActor(menuWindow);
			}
		});
		
		menuButtonTable = new Table();
		menuButtonTable.add(menuButton);

		wrapper.add(menuButtonTable);
		menuStage.addActor(wrapper);
	}

	@Override
	public void render(float delta) {
		stage.draw();
		menuStage.draw();
		Table.drawDebug(menuStage);
	}

	private Entity touchedEntity;
	private float shiftX;
	private float shiftY;

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		menuStage.touchDown(x, y, pointer, button);
		
		System.out.println("TOUCHED DOWN");
		for (Entity entity : pangolinWorld.getEntities()) {
			if (entity.touchDown(x, y)) {
				touchedEntity = entity;
				// TODO There's a little bug when an entity is dragged (quick shift to the pointer position, not good looking)
//				shiftX = (x * (getWidth() / Gdx.graphics.getWidth())) - entity.getEntityGraphic().getX();
//				shiftY = entity.getEntityGraphic().getY() - (getHeight() - (y * (getHeight() / Gdx.graphics.getHeight())));
				break;
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		System.out.println("TOUCHED DRAGGED");
		if (touchedEntity != null) {
			touchedEntity.setPosition(new Vector2(GameUtil.projectCoordinateX(x), GameUtil.projectCoordinateY(y)));
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		menuStage.touchUp(x, y, pointer, button);
		
		System.out.println("TOUCHED UP");
		touchedEntity = null;
		return false;
	}

}
