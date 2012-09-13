package fr.gravity.pangolin;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

import fr.gravity.pangolin.exception.InvalidMapException;
import fr.gravity.pangolin.menu.MainMenuScreen;

public class GravityPangolinGame extends Game {

	private static GravityPangolinGame instance;

	private Screen mainMenuScreen;
	private Screen gravityPangolinScreen;

	private GravityPangolinGame() {
	}

	public static GravityPangolinGame getInstance() {
		if (instance == null)
			instance = new GravityPangolinGame();
		return instance;
	}

	@Override
	public void create() {
		TextureLoader.getInstance();
		launchGame();
		// setScreen(getMainMenuScreen());
	}

	public void launchGame() {
		PangolinWorld pangolinWorld = PangolinWorld.getInstance(Gdx.files.internal("data/map1.pm"));
		setScreen(getGameScreen(pangolinWorld));
		pangolinWorld.init();
	}

	public void setScreen(Screen screen) {
		super.setScreen(screen);
		Gdx.input.setInputProcessor((InputProcessor) screen);
	}

	private Screen getMainMenuScreen() {
		if (mainMenuScreen == null)
			mainMenuScreen = new MainMenuScreen();
		return mainMenuScreen;
	}

	private Screen getGameScreen(PangolinWorld pangolinWorld) {
		if (gravityPangolinScreen == null)
			gravityPangolinScreen = new GravityPangolinScreen(pangolinWorld);
		return gravityPangolinScreen;
	}

}
