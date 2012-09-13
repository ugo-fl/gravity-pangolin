package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;
import fr.gravity.pangolin.entity.EntityGraphic;

public class BranchBlockGraphic extends EntityGraphic {

	private TextureRegion textureRegion;
	private BranchFramePos branchFramePos;

	public enum BranchFramePos {
		START, MIDDLE, END
	}

	public BranchBlockGraphic(float x, float y, BranchFramePos branchFramePos) {
		this.branchFramePos = branchFramePos;
		loadTexture();
		init(textureRegion, x, y);
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

}
