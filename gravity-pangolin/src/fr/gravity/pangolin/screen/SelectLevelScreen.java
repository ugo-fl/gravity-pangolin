package fr.gravity.pangolin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

import fr.gravity.pangolin.GravityPangolinGame;

public class SelectLevelScreen extends AbstractScreen2 {

	private static final int MAX_ROW = 5;
	private static final int MAX_COL = 5;

	public SelectLevelScreen(GravityPangolinGame game) {
		super(game, null);
	}

	@Override
	public void show() {
		super.show();

		FileHandle[] maps = Gdx.files.internal("./bin/map").list();

		Table table = super.getTable();
		table.add("Select level").spaceBottom(50);
		table.row();

		Skin skin = getSkin();
		for (int i = 0; i < MAX_ROW; i++) {
			for (int j = 0; j < MAX_COL; j++) {
				TextButton textButton = new TextButton(skin);
				if (i * MAX_COL + j < maps.length) {
					final FileHandle mapFile = maps[i * MAX_COL + j];
					textButton.setText(maps[i * MAX_COL + j]
							.nameWithoutExtension());
					textButton.setClickListener(new ClickListener() {
						@Override
						public void click(Actor actor, float x, float y) {
							GravityPangolinGame.getInstance().start(
									mapFile);
						}
					});
				}
				table.add(textButton).size(75, 75).uniform().fill();
			}
			table.row();
		}

	}
}
