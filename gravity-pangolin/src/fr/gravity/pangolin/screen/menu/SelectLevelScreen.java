package fr.gravity.pangolin.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;

import fr.gravity.pangolin.game.GameProgress;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.util.GameUtil;

public class SelectLevelScreen extends MenuScreen {

	private static final int MAX_ROW = 5;
	private static final int MAX_COL = 5;

	private int packId;

	public SelectLevelScreen(GravityPangolinGame game, int packId) {
		super(game, null);
		this.packId = packId;
	}

	@Override
	public void show() {
		super.show();

		loadBackButton(new ClickListener() {
			@Override
			public void click(Actor actor, float x, float y) {
				GravityPangolinGame.getInstance().showSelectPackScreen();
			}
		});
		loadMapItems();
	}

	private void loadMapItems() {
		final int BUTTON_WIDTH = 40;
		final int BUTTON_HEIGHT = 40;

//		Gdx.input.setInputProcessor(stage);
		
		FileHandle[] maps = game.getPacks()[packId].getMaps();

		Table table = super.getTable();
		table.add("Select level").spaceBottom(50);
		table.row();

		int progressLevelId = GameProgress.getInstance().getLevelId();
		Skin skin = getSkin();
		for (int i = 0; i < MAX_ROW; i++) {
			for (int j = 0; j < MAX_COL; j++) {
				if (i * MAX_COL + j >= maps.length)
					break;

				TextButton textButton = new TextButton(skin);
				final int levelId = i * MAX_COL + j;
				final int packId = i;
				final boolean isLocked = levelId > progressLevelId;

				textButton.setText(maps[i * MAX_COL + j].nameWithoutExtension());
				textButton.setClickListener(new ClickListener() {
					@Override
					public void click(Actor actor, float x, float y) {
						if (!isLocked)
							GravityPangolinGame.getInstance().start(packId, levelId);
					}
				});
				if (isLocked) {
					Image lockImage = new Image(new Texture(Gdx.files.internal("images/lock_min.png")));
					lockImage.setFillParent(true);
					textButton.addActor(lockImage);
				}
				table.add(textButton).size(BUTTON_WIDTH, BUTTON_HEIGHT).uniform().fill();
			}
			table.row();
		}
	}
}
