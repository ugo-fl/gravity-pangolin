package fr.gravity.pangolin.screen;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.entity.menu.ButtonsSlider;
import fr.gravity.pangolin.entity.menu.CustomButton;

public class SelectLevelScreen extends AbstractScreen {

	private static final int MAX_ROW = 5;
	private static final int MAX_COL = 5;

	public SelectLevelScreen(GravityPangolinGame game) {
		super(game, null);
	}

	@Override
	public void show() {
		super.show();

		drawPacks();
		// drawMapItems(maps);

	}

	private void drawPacks() {
		final int BUTTON_WIDTH = 100;
		final int BUTTON_HEIGHT = 100;

		final int SELECTED_X = (width / 2) - (BUTTON_WIDTH / 2);
		final int SELECTED_Y = (height / 2) - (BUTTON_HEIGHT / 2);
		final int PADDING = 10;

		FileHandle[] packs = Gdx.files.internal("./bin/map").list();
		Skin skin = getSkin();

		stage.clear();
		List<TextButton> textButtons = new ArrayList<TextButton>();
		for (int i = 0; i < packs.length; i++) {
			final FileHandle pack = packs[i];

			TextButton textButton = new TextButton(skin);
			textButton.setText(pack.nameWithoutExtension().substring(2).replace(' ', '\n'));
			textButton.setClickListener(new ClickListener() {
				@Override
				public void click(Actor actor, float x, float y) {
					drawMapItems(pack.list());
				}
			});
			textButtons.add(textButton);
		}
		stage.addActor(new ButtonsSlider(textButtons, stage));
	}

	private void drawMapItems(FileHandle[] maps) {
		final int BUTTON_WIDTH = 40;
		final int BUTTON_HEIGHT = 40;

		stage.clear();

		Table table = super.getTable();
		table.add("Select level").spaceBottom(50);
		table.row();

		Skin skin = getSkin();
		for (int i = 0; i < MAX_ROW; i++) {
			for (int j = 0; j < MAX_COL; j++) {
				TextButton textButton = new TextButton(skin);
				if (i * MAX_COL + j < maps.length) {
					final FileHandle mapFile = maps[i * MAX_COL + j];
					textButton.setText(maps[i * MAX_COL + j].nameWithoutExtension());
					textButton.setClickListener(new ClickListener() {
						@Override
						public void click(Actor actor, float x, float y) {
							GravityPangolinGame.getInstance().start(mapFile);
						}
					});
				}
				table.add(textButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).uniform().fill();
			}
			table.row();
		}
	}
}
