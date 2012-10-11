package fr.gravity.pangolin.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;

import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.screen.GravityPangolinScreen;
import fr.gravity.pangolin.screen.IScreen;
import fr.gravity.pangolin.screen.TransitionScreen;
import fr.gravity.pangolin.screen.menu.MainMenuScreen;
import fr.gravity.pangolin.screen.menu.SelectLevelScreen;
import fr.gravity.pangolin.screen.menu.SelectPackScreen;
import fr.gravity.pangolin.transition.FadeInTransition;
import fr.gravity.pangolin.transition.FadeOutTransition;
import fr.gravity.pangolin.transition.Transition;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class GravityPangolinGame extends Game {

	public static final String LOG = GravityPangolinGame.class.getSimpleName();;
	private static GravityPangolinGame instance;

	private GravityPangolinWorld pangolinWorld;
	private GravityPangolinScreen gravityPangolinScreen;

	/*
	 * SCREENS
	 */
	private Screen mainMenuScreen;
	private Screen selectPackScreen;
	private Screen selectLevelScreen;
	private Screen youWinScreen;
	private Screen gameOverScreen;

	/**
	 * THIS IS ONLY FOR DESKTOP APP
	 */
	public static final String MAP_DIRECTORY = "./bin/map";
	/**
	 * FOR ANDROID PHONES
	 */
	// public static final String MAP_DIRECTORY = "map";

	private Pack[] packs;

	private int packId;
	private int levelId;

	private GravityPangolinGame() {
	}

	public static GravityPangolinGame getInstance() {
		if (instance == null)
			instance = new GravityPangolinGame();
		return instance;
	}

	@Override
	public void create() {
		// Loads all the textures (by getting TextureLoader's instance)
		TextureHelper.getInstance();

		// Loads all the screens
		 loadScreens();

		// Loads all the levels
		loadLevels();

		// start(0, 0);
		// setScreen(new TestBox2DScreen());
		 setScreen(mainMenuScreen);
	}

	private void loadScreens() {
		mainMenuScreen = new MainMenuScreen(this);
		selectPackScreen = new SelectPackScreen(this);
//		youWinScreen = new YouWinScreen(null, null);
//		gameOverScreen = new GameOverScreen(null, null);
	}

	private void loadLevels() {
		FileHandle[] packDirectories = Gdx.files.internal(MAP_DIRECTORY).list();
		packs = new Pack[packDirectories.length];

		int index = 0;
		for (FileHandle packDirectory : packDirectories) {
			packs[index++] = new Pack(packDirectory.nameWithoutExtension().substring(2).replace(' ', '\n'), packDirectory);
		}
	}

	public void start(int packId, int levelId) {
		this.packId = packId;
		this.levelId = levelId;

		pangolinWorld = GravityPangolinWorld.getInstance(getLevelMap(packId, levelId));
		gravityPangolinScreen = new GravityPangolinScreen(this, pangolinWorld);
		
		ArrayList<Transition> transitionEffects = new ArrayList<Transition>();
		transitionEffects.add(new FadeOutTransition(1000));
		transitionEffects.add(new FadeInTransition(1000));

		TransitionScreen transitionScreen = new TransitionScreen(this, (IScreen) getScreen(), gravityPangolinScreen, transitionEffects);
		setScreen(transitionScreen);
		
		GameProgress.getInstance().setProgress(packId, levelId);
	}

	private FileHandle getLevelMap(int packId, int levelId) {
		return packs[packId].getMap(levelId);
	}

	public void restart() {
		start(packId, levelId);
	}

	public void nextStage() {
		levelId++;
		if (levelId >= packs[packId].getMaps().length) {
			levelId = 0;
			showSelectPackScreen();
		} else
			start(packId, levelId);
	}

	public void previousStage() {
		levelId--;
		if (levelId < 0) {
			levelId = 0;
			showSelectPackScreen();
		} else
			start(packId, levelId);
	}

	/*
	 * METHOD FOR SHOWING SCREENS
	 */

	public void gameOver() {
		setScreen(gameOverScreen);
	}

	public void youWin() {
		setScreen(youWinScreen);
	}

	public void showSelectPackScreen() {
		setScreen(new SelectPackScreen(this));
	}

	public void showSelectLevelScreen(Screen selectLevelScreen) {
		this.selectLevelScreen = selectLevelScreen;
		showSelectLevelScreen();
	}

	public void showSelectLevelScreen() {
		// TODO Why isn't that working ?
		// setScreen(selectLevelScreen);

		setScreen(new SelectLevelScreen(this, packId));
	}

	public void showMainMenuScreen() {
		setScreen(mainMenuScreen);
	}

	/**
	 * GETTERS
	 */

	public GravityPangolinWorld getPangolinWorld() {
		return pangolinWorld;
	}

	public Pack[] getPacks() {
		return packs;
	}

}
