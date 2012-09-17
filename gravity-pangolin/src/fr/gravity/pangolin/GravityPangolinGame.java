package fr.gravity.pangolin;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import fr.gravity.pangolin.screen.GameOverScreen;
import fr.gravity.pangolin.screen.GravityPangolinScreen;
import fr.gravity.pangolin.screen.MainMenuScreen;
import fr.gravity.pangolin.screen.YouWinScreen;

public class GravityPangolinGame extends Game {

	private static GravityPangolinGame instance;

//	private Screen mainMenuScreen;
//	private Screen gravityPangolinScreen;

	private GravityPangolinGame() {
	}

	public static GravityPangolinGame getInstance() {
		if (instance == null)
			instance = new GravityPangolinGame();
		return instance;
	}

	@Override
	public void create() {
		// Loads all the textures before launching the game
		TextureLoader.getInstance();
		
//		launchGame();
		setScreen(new MainMenuScreen());
	}

	// TODO This is terrible code. Needs refactoring.
	public void startNewGame() {
		PangolinWorld pangolinWorld = PangolinWorld.getInstance(Gdx.files
				.internal("data/map1.pm"));
		GravityPangolinScreen gravityPangolinScreen = new GravityPangolinScreen(pangolinWorld);
		
		setScreen(gravityPangolinScreen);
//		resize((int) gravityPangolinScreen.getWidth(), (int) gravityPangolinScreen.getHeight());
		
		pangolinWorld.init();
		gravityPangolinScreen.init();

	}
	
	public void gameOver() {
		setScreen(new GameOverScreen());
	}

	public void youWin() {
		setScreen(new YouWinScreen());
	}
	
//	public void setScreen(Screen screen) {
//		super.setScreen(screen);
//		Gdx.input.setInputProcessor((InputProcessor) screen);
//	}

//	private Screen getMainMenuScreen() {
//		if (mainMenuScreen == null)
//			mainMenuScreen = new MainMenuScreen();
//		return mainMenuScreen;
//	}
//
//	private Screen getGameScreen(PangolinWorld pangolinWorld) {
//		if (gravityPangolinScreen == null)
//			gravityPangolinScreen = new GravityPangolinScreen(pangolinWorld);
//		return gravityPangolinScreen;
//	}

}
