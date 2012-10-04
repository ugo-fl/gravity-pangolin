package fr.gravity.pangolin.entity.graphic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;

public class BranchBlockGraphic extends EntityGraphic {

	public enum BranchFramePosition {
		START, MIDDLE, END
	}

	public BranchBlockGraphic(float x, float y, BranchFramePosition branchFramePosition) {
		super(TextureHelper.getInstance().getTextureRegions(TextureId.BRANCH)[branchFramePosition.ordinal()], x, y);
	}

	@Override
	public void draw (SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}
	
	@Override
	public void touchDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchDownOut() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchUpOut() {
		// TODO Auto-generated method stub
		
	}

}
