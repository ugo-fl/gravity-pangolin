package fr.gravity.pangolin;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import fr.gravity.pangolin.screen.GameOverScreen;
import fr.gravity.pangolin.screen.GravityPangolinScreen;
import fr.gravity.pangolin.screen.MainMenuScreen;
import fr.gravity.pangolin.screen.SelectLevelScreen;
import fr.gravity.pangolin.screen.YouWinScreen;

public class GravityPangolinGame extends Game {

	public static final String LOG = GravityPangolinGame.class.getSimpleName();;
	private static GravityPangolinGame instance;

	// private Screen mainMenuScreen;
	// private Screen gravityPangolinScreen;

	private FileHandle mapFile;
	private PangolinWorld pangolinWorld;

	// private PangolinWorld pangolinWorld;

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

		// start(Gdx.files.internal("map/map1.pm"));
		setScreen(new MainMenuScreen(this));
	}

	// TODO This is terrible code. Needs refactoring.
	public void start(FileHandle mapFile) {
		this.mapFile = mapFile;
		pangolinWorld = PangolinWorld.getInstance(mapFile);
		GravityPangolinScreen gravityPangolinScreen = new GravityPangolinScreen(
				this);

		setScreen(gravityPangolinScreen);
		pangolinWorld.init();
		gravityPangolinScreen.init();
	}

	public void restart() {
		start(mapFile);
	}

	public void gameOver() {
		setScreen(new GameOverScreen());
	}

	public void youWin() {
		setScreen(new YouWinScreen());
	}

	public void showSelectLevelScreen() {
		setScreen(new SelectLevelScreen(this));
	}

	public PangolinWorld getPangolinWorld() {
		return pangolinWorld;
	}

	
	// public void setScreen(Screen screen) {
	// super.setScreen(screen);
	// Gdx.input.setInputProcessor((InputProcessor) screen);
	// }

	// private Screen getMainMenuScreen() {
	// if (mainMenuScreen == null)
	// mainMenuScreen = new MainMenuScreen();
	// return mainMenuScreen;
	// }
	//
	// private Screen getGameScreen(PangolinWorld pangolinWorld) {
	// if (gravityPangolinScreen == null)
	// gravityPangolinScreen = new GravityPangolinScreen(pangolinWorld);
	// return gravityPangolinScreen;
	// }

}
