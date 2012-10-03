package fr.gravity.pangolin.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameProgress {

	private static final String LEVEL_ID = "levelId";

	private static final String PACK_ID = "packId";

	/**
	 * Singleton instance
	 */
	private static GameProgress instance;

	/**
	 * Preferences
	 */
	private final Preferences progressPreferences = Gdx.app.getPreferences("gp_progress");

	private final static int DEFAULT_PACK_ID = 0;
	private final static int DEFAULT_LEVEL_ID = 0;

	public static GameProgress getInstance() {
		if (instance == null)
			instance = new GameProgress();
		if (instance.progressPreferences.getInteger(PACK_ID) == 0)
			instance.setProgress(DEFAULT_PACK_ID, DEFAULT_LEVEL_ID);
		return instance;
	}

	private GameProgress() {
	}

	public void setProgress(int packId, int levelId) {
		if (packId < progressPreferences.getInteger(PACK_ID))
			return;
		else if (packId == progressPreferences.getInteger(PACK_ID) && levelId <= progressPreferences.getInteger(LEVEL_ID))
			return;
		
		progressPreferences.putInteger(PACK_ID, packId);
		progressPreferences.putInteger(LEVEL_ID, levelId);
		progressPreferences.flush();
	}

	public int getPackId() {
		return progressPreferences.getInteger(PACK_ID);
	}

	public int getLevelId() {
		return progressPreferences.getInteger(LEVEL_ID);
	}
}
