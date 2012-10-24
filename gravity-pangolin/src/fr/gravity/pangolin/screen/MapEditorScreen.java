package fr.gravity.pangolin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.util.GameUtil;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class MapEditorScreen extends GravityPangolinScreen {

	public MapEditorScreen(GravityPangolinGame gravityPangolinGame, GravityPangolinWorld pangolinWorld) {
		super(gravityPangolinGame, pangolinWorld);
	}

	@Override
	public void show() {
		Gdx.app.log(GravityPangolinGame.LOG, "Showing screen: " + getName());
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		stage.draw();
	}

	private Entity touchedEntity;
	private float shiftX;
	private float shiftY;

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
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
		System.out.println("TOUCHED UP");
		touchedEntity = null;
		return false;
	}

}
