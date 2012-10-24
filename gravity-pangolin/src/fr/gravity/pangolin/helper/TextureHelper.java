package fr.gravity.pangolin.helper;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.constant.DirectoryConstant;

public class TextureHelper {

	/**
	 * Singleton instance
	 */
	private static TextureHelper instance;

	/**
	 * Texture ids containing texture and size
	 */
	public enum TextureId {
		PANGOLIN(new Texture(DirectoryConstant.IMAGE_DIR + "sprite_pangolin.png"), 1, 2),
		BACKGROUND(new Texture(DirectoryConstant.IMAGE_DIR + "background.png"), 1, 1),
		BUTTONS(new Texture(DirectoryConstant.IMAGE_DIR + "buttons_sprite.png"), 1, 2), 
		WALL_BLOCK(new Texture(DirectoryConstant.IMAGE_DIR + "wall_block.png"), 1, 1),
		GRAVITY_CHANGER(new Texture(DirectoryConstant.IMAGE_DIR + "gravity_changer.png"), 1, 1),
		PANGOLIN_BALLMODE(new Texture(DirectoryConstant.IMAGE_DIR + "sprite_pangolin_ballmode.png"), 1, 4),
		EXIT(new Texture(DirectoryConstant.IMAGE_DIR + "exit.png"), 1, 1),
		YOU_WIN(new Texture(DirectoryConstant.IMAGE_DIR + "you_win.png"), 1, 1),
		GAME_OVER(new Texture(DirectoryConstant.IMAGE_DIR + "game_over.png"), 1, 1), 
		HARMFUL_BLOCK(new Texture(DirectoryConstant.IMAGE_DIR + "harmful_block.png"), 1, 1), 
		STAR_FISH(new Texture(DirectoryConstant.IMAGE_DIR + "star_fish.png"), 1, 1),
		PROJECTILE(new Texture(DirectoryConstant.IMAGE_DIR + "projectile.png"), 1, 1), 
		SPADES_BLOCK(new Texture(DirectoryConstant.IMAGE_DIR + "spades.png"), 1, 1);

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
	private HashMap<TextureId, TextureRegion[]> textureMap = new HashMap<TextureHelper.TextureId, TextureRegion[]>();

	/**
	 * Singleton accessor.
	 * @return the unique instance of the TextureLoader
	 */
	public static TextureHelper getInstance() {
		if (instance == null)
			instance = new TextureHelper();
		return instance;
	}

	/**
	 * Instantiate all the textures for the game.
	 */
	private TextureHelper() {
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
