package fr.gravity.pangolin.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameSettings {

	/**
	 * Singleton instance
	 */
	private GameSettings instance;
	
	/**	PREFERENCES **/
	
	private final Preferences settingsPreferences = Gdx.app.getPreferences("gp_settings");
	private final Preferences progressPreferences = Gdx.app.getPreferences("gp_progress");
	
	public GameSettings getInstance() {
		if (instance == null)
			instance = new GameSettings();
		return instance;
	}
	
	private GameSettings() {
	}
	
}
