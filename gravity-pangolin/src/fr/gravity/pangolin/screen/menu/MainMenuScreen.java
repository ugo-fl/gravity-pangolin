package fr.gravity.pangolin.screen.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import fr.gravity.pangolin.game.GravityPangolinGame;

public class MainMenuScreen extends MenuScreen {
	private TextButton startButton;
	
	public MainMenuScreen(GravityPangolinGame gravityPangolinGame) {
		super(gravityPangolinGame, null);
		loadButtons();
	}

	private void loadButtons() {
		startButton = new TextButton("Go !", getSkin());
		startButton.x = 100;
		startButton.y = 200;

		startButton.setClickListener(new ClickListener() {
			@Override
			public void click(Actor actor, float x, float y) {
				GravityPangolinGame.getInstance().showSelectPackScreen();
			}
		});
		stage.addActor(startButton);
	}

}
