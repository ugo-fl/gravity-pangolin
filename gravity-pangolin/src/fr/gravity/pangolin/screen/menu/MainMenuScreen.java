package fr.gravity.pangolin.screen.menu;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.gravity.pangolin.game.GravityPangolinGame;

public class MainMenuScreen extends MenuScreen {
	private TextButton startButton;
	
	public MainMenuScreen(GravityPangolinGame gravityPangolinGame) {
		super(gravityPangolinGame, null);
		loadButtons();
	}

	private void loadButtons() {
		startButton = new TextButton("Go !", getSkin());
		startButton.setX(100);
		startButton.setY(200);

		startButton.addListener(new ClickListener() {
			public void clicked (InputEvent event, float x, float y) {
				GravityPangolinGame.getInstance().showSelectPackScreen();
			}
		});
		stage.addActor(startButton);
	}

}
