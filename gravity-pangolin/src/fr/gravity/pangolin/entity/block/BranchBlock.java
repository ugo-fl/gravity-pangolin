package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;
import fr.gravity.pangolin.entity.Entity;

public class BranchBlock extends Entity implements Block {

//	public static float WIDTH = 1F;
//	public static float HEIGHT = 0.3F;

	private TextureRegion textureRegion;
	private BranchFramePos branchFramePos;

	public enum BranchFramePos {
		START, MIDDLE, END
	}

	public BranchBlock(float x, float y, BranchFramePos branchFramePos) {
		this.branchFramePos = branchFramePos;
		loadTexture();
		init(textureRegion, x, y);
//		setSize(WIDTH, HEIGHT);
	}

	private void loadTexture() {
		TextureRegion[] textureRegions = TextureLoader.getInstance().getTextureRegions(TextureId.BRANCH);
		int index = 0;
		if (branchFramePos == BranchFramePos.MIDDLE)
			index = 1;
		else if (branchFramePos == BranchFramePos.END)
			index = 2;
		textureRegion = textureRegions[index];
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
	public Rectangle getBoundingRectangle() {
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
