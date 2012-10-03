package fr.gravity.pangolin.screen;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;
import fr.gravity.pangolin.world.PangolinWorld;

public class GravityPangolinScreen extends AbstractScreen {

	private PangolinWorld pangolinWorld;

	public GravityPangolinScreen(GravityPangolinGame gravityPangolinGame, PangolinWorld pangolinWorld) {
		super(gravityPangolinGame, pangolinWorld);
		this.pangolinWorld = pangolinWorld;
	}

	@Override
	public void show() {
		super.show();
		
		loadBackground();
		pangolinWorld.init(stage);
		
		loadBackButton(new ClickListener() {
			@Override
			public void click(Actor actor, float x, float y) {
				GravityPangolinGame.getInstance().showSelectLevelScreen();
			}
		});
		
		stage.setKeyboardFocus(pangolinWorld.getPangolin());
	}

	private void loadBackground() {
		Image background = new Image(TextureHelper.getInstance().getTextureRegions(TextureId.BACKGROUND)[0]);
		background.setFillParent(true);
		stage.addActor(background);
	}


}
