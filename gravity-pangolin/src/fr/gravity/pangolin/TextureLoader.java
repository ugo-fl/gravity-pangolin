package fr.gravity.pangolin;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureLoader {

	/**
	 * Singleton instance
	 */
	private static TextureLoader instance;

	/**
	 * Texture ids containing texture and size
	 */
	public enum TextureId {
		PANGOLIN(new Texture("images/sprite_pangolin.png"), 1, 2),
		BACKGROUND(new Texture("images/background.png"), 1, 1),
		BUTTONS(new Texture("images/buttons_sprite.png"), 1, 2), 
		BRANCH(new Texture("images/branche.png"), 1, 3),
		GRAVITY_CHANGER(new Texture("images/gravity_changer.png"), 1, 1),
		PANGOLIN_BALLMODE(new Texture("images/sprite_pangolin_ballmode.png"), 1, 4),
		EXIT(new Texture("images/exit.png"), 1, 1),
		YOU_WIN(new Texture("images/you_win.png"), 1, 1),
		GAME_OVER(new Texture("images/game_over.png"), 1, 1);

		private final Texture texture;
		private final int frameRows;
		private final int frameCols;

		private TextureId(Texture texture, int frameRows, int frameCols) {
			this.texture = texture;
			this.frameRows = frameRows;
			this.frameCols = frameCols;
		}

		public Texture getTexture() {
			return texture;
		}

		public int getFrameRows() {
			return frameRows;
		}

		public int getFrameCols() {
			return frameCols;
		}

	}

	/**
	 * The map containing all the textures of the game
	 */
	private HashMap<TextureId, TextureRegion[]> textureMap = new HashMap<TextureLoader.TextureId, TextureRegion[]>();

	/**
	 * Singleton accessor.
	 * @return the unique instance of the TextureLoader
	 */
	public static TextureLoader getInstance() {
		if (instance == null)
			instance = new TextureLoader();
		return instance;
	}

	/**
	 * Instantiate all the textures for the game.
	 */
	private TextureLoader() {
		for (TextureId textureId : TextureId.values()) {
			textureMap.put(textureId,
					loadSprites(textureId.getTexture(), textureId.getFrameRows(), textureId.getFrameCols()));
		}
	}

	/**
	 * Divides the source into n TextureRegions where n = frameRows * frameCols. 
	 * @param source
	 * @param frameRows
	 * @param frameCols
	 * @return
	 */
	private TextureRegion[] loadSprites(Texture source, int frameRows, int frameCols) {
		TextureRegion[] result = new TextureRegion[frameRows * frameCols];
		TextureRegion[][] tmp = TextureRegion.split(source, source.getWidth() / frameCols, source.getHeight()
				/ frameRows);
		int index = 0;
		for (int i = 0; i < frameRows; i++) {
			for (int j = 0; j < frameCols; j++) {
				result[index++] = tmp[i][j];
			}
		}
		return result;
	}

	public TextureRegion[] getTextureRegions(TextureId textureId) {
		return textureMap.get(textureId);
	}

	public Sprite getSingleSprite(TextureId textureId) {
		return new Sprite(getTextureRegions(textureId)[0]);
	}
	
}
