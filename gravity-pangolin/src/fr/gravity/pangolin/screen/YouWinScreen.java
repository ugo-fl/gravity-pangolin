package fr.gravity.pangolin.screen;

import java.util.Date;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import fr.gravity.pangolin.GravityPangolinGame;
import fr.gravity.pangolin.PangolinWorld;
import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;

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
		background = new Image(TextureLoader.getInstance().getSingleSprite(TextureId.YOU_WIN));
		stage.addActor(background);
//		background.setSize(width, height);
//		background.setPosition(-width / 2, -height / 2);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (new Date().getTime() - timestamp > DISPLAY_PERIOD)
			GravityPangolinGame.getInstance().restart();
	}
}
