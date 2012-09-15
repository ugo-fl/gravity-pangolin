package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;
import fr.gravity.pangolin.entity.EntityGraphic;

public class BranchBlockGraphic extends EntityGraphic {

	private BranchFramePosition branchFramePos;

	public enum BranchFramePosition {
		START, MIDDLE, END
	}

	public BranchBlockGraphic(float x, float y, BranchFramePosition branchFramePos) {
		this.branchFramePos = branchFramePos;
		set(getSprite(), x, y);
	}

	private Sprite getSprite() {
		TextureRegion[] textureRegions = TextureLoader.getInstance().getTextureRegions(TextureId.BRANCH);
		int index = 0;
		if (branchFramePos == BranchFramePosition.MIDDLE)
			index = 1;
		else if (branchFramePos == BranchFramePosition.END)
			index = 2;
		return new Sprite(textureRegions[index]);
	}

}
