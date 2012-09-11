package fr.gravity.pangolin;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;

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
		// setScreen(new GravityPangolinScreen());

		if (mainMenuScreen == null)
			mainMenuScreen = new MainMenuScreen();
		setScreen(mainMenuScreen);
		Gdx.input.setInputProcessor((InputProcessor) mainMenuScreen);
	}

	public void launchGame() {
		if (gravityPangolinScreen == null)
			gravityPangolinScreen = new GravityPangolinScreen();
		setScreen(gravityPangolinScreen);
		Gdx.input.setInputProcessor((InputProcessor) gravityPangolinScreen);
	}
	
}
