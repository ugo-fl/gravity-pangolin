package fr.gravity.pangolin.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ExitBlock extends Block {

	public static int EXIT_DOWN = 0;
	public static int EXIT_LEFT = 1;
	public static int EXIT_UP = 2;
	public static int EXIT_RIGHT = 3;

	public static float WIDTH = 1F;
	public static float HEIGHT = 0.3F;
	
	private int exitSide = EXIT_DOWN;

	private TextureRegion textureRegion;

	public ExitBlock(Vector2 pos, int exitSide) {
		super(pos);
		this.exitSide = exitSide;
		bounds.setY(bounds.y + 0.8F);
		setSize(WIDTH, HEIGHT);
	}

	@Override
	public boolean collides() {
		return false;
	}

	@Override
	public TextureRegion getTextureRegion() {
		if (textureRegion == null) {
			Texture gravityChangerTexture = new Texture(Gdx.files.internal("images/exit.png"));
			textureRegion = new TextureRegion(gravityChangerTexture);
		}
		return textureRegion;
	}

}
