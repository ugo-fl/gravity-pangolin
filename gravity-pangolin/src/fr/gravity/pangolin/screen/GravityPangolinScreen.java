package fr.gravity.pangolin.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class GravityPangolinScreen extends AbstractScreen {

	protected GravityPangolinWorld pangolinWorld;

	public GravityPangolinScreen(GravityPangolinGame gravityPangolinGame, GravityPangolinWorld pangolinWorld) {
		super(gravityPangolinGame, pangolinWorld, pangolinWorld.getSizeX(), pangolinWorld.getSizeY());
		this.pangolinWorld = pangolinWorld;

		loadBackground();
		pangolinWorld.addActorsToStage(stage);
		// pangolinWorld.addAll(stage);

		loadBackButton(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				GravityPangolinGame.getInstance().showSelectLevelScreen();
			}
		});

		Gdx.input.setInputProcessor(pangolinWorld.getPangolin());
//		stage.setKeyboardFocus(pangolinWorld.getPangolin());
	}

	private void loadBackground() {
		Image background = new Image(TextureHelper.getInstance().getTextureRegions(TextureId.BACKGROUND)[0]);
		background.setFillParent(true);
		stage.addActor(background);
	}

}
