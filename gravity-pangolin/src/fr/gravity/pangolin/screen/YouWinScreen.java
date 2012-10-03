package fr.gravity.pangolin.screen;

import java.util.Date;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;
import fr.gravity.pangolin.world.PangolinWorld;

public class YouWinScreen extends AbstractScreen {

	private static final long DISPLAY_PERIOD = 3000;
	private long timestamp;

	private Image background;

	public YouWinScreen(GravityPangolinGame game, PangolinWorld pangolinWorld) {
		super(game, pangolinWorld);
	}

	@Override
	public void show() {

		loadBackgroundSprite();
		timestamp = new Date().getTime();
	}

	private void loadBackgroundSprite() {
		background = new Image(TextureHelper.getInstance().getSingleSprite(TextureId.YOU_WIN));
		stage.addActor(background);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (new Date().getTime() - timestamp > DISPLAY_PERIOD)
			GravityPangolinGame.getInstance().nextStage();
	}
}
