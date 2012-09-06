package fr.gravity.pangolin.block;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BranchBlock extends Block {

	public static float WIDTH = 1F;
	public static float HEIGHT = 0.3F;

	private static final int FRAME_COLS = 3;
	private static final int FRAME_ROWS = 1;

	private TextureRegion textureRegion;
	private BranchFramePos branchFramePos;

	public enum BranchFramePos {
		START, MIDDLE, END
	}

	public BranchBlock(Vector2 pos, BranchFramePos branchFramePos) {
		super(pos);
		this.branchFramePos = branchFramePos;
		loadTextures();
		setSize(WIDTH, HEIGHT);
	}

	private void loadTextures() {
		Texture brancheTexture = new Texture(Gdx.files.internal("images/branche.png"));
		TextureRegion[][] branchFrames = TextureRegion.split(brancheTexture, brancheTexture.getWidth() / FRAME_COLS,
				brancheTexture.getHeight() / FRAME_ROWS);
		int index = 0;
		if (branchFramePos == BranchFramePos.MIDDLE)
			index = 1;
		else if (branchFramePos == BranchFramePos.END)
			index = 2;
		textureRegion = branchFrames[0][index];
		textureRegion.flip(false, true);
	}

	@Override
	public boolean collides() {
		return true;
	}

	@Override
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}

	@Override
	public Rectangle getTextureBounds() {
		float gapX = 0F;
		float gapWidth = 0F;
		if (branchFramePos == BranchFramePos.START) {
			gapX = -0.6F;
			gapWidth = 0.6F;
		}
		else if (branchFramePos == BranchFramePos.END) {
			gapWidth = 0.6F;
		}
		return new Rectangle(gapX, 0, 1F + gapWidth, 1.3F);
	}

}
