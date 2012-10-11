package fr.gravity.pangolin.screen.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import fr.gravity.pangolin.constant.DirectoryConstant;
import fr.gravity.pangolin.entity.menu.ButtonsSlider;
import fr.gravity.pangolin.game.GameProgress;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.game.Pack;

public class SelectPackScreen extends MenuScreen {

	public SelectPackScreen(GravityPangolinGame game) {
		super(game, null);
	}

	@Override
	public void show() {
		super.show();
		
		drawButtonsSlider();
		loadBackButton(new ClickListener() {
			@Override
			public void click(Actor actor, float x, float y) {
				GravityPangolinGame.getInstance().showMainMenuScreen();
			}
		});
	}

	private void drawButtonsSlider() {
		// FileHandle[] packs = Gdx.files.internal("./bin/map").list();
		Pack[] packs = game.getPacks();
		Skin skin = getSkin();

		int progressPackId = GameProgress.getInstance().getPackId();

		List<TextButton> textButtons = new ArrayList<TextButton>();
		for (int i = 0; i < packs.length; i++) {
			final Pack pack = packs[i];
			final int packId = i;
			final boolean isLocked = i > progressPackId;
			TextButton textButton = new TextButton(skin);

			// Adding text right under the button
			// TODO Values are static, text is not centered
			Label l = new Label(pack.getName(), skin);
			l.y = -25;
			textButton.addActor(l);
			// textButton.setText(pack.nameWithoutExtension().substring(2).replace(' ',
			// '\n'));

			textButton.setClickListener(new ClickListener() {
				@Override
				public void click(Actor actor, float x, float y) {
					if (!isLocked)
						GravityPangolinGame.getInstance().showSelectLevelScreen(new SelectLevelScreen(game, packId));
				}
			});
			if (isLocked) {
				Image lockImage = new Image(new Texture(Gdx.files.internal(DirectoryConstant.IMAGE_DIR + "lock.png")));
				lockImage.setFillParent(true);
				textButton.addActor(lockImage);
			}

			textButtons.add(textButton);
		}
		stage.addActor(new ButtonsSlider(textButtons, stage, this));
	}

}
