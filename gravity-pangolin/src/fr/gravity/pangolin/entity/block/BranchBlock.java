package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import fr.gravity.pangolin.TextureLoader;
import fr.gravity.pangolin.TextureLoader.TextureId;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.block.BranchBlockGraphic.BranchFramePos;

public class BranchBlock extends Entity {

	private BranchFramePos branchFramePos;
	
	public BranchBlock(float x, float y, BranchFramePos branchFramePos) {
		this.branchFramePos = branchFramePos;
		entityGraphic = new BranchBlockGraphic(x, y, branchFramePos);
	}

	@Override
	public boolean collides() {
		return true;
	}

	@Override
	public void draw(SpriteBatch spriteBatch) {
		entityGraphic.draw(spriteBatch);
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
