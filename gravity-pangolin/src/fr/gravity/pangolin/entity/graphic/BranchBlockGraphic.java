package fr.gravity.pangolin.entity.graphic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.entity.EntityGraphic;
import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;

public class BranchBlockGraphic extends EntityGraphic {

	public enum BranchFramePosition {
		START, MIDDLE, END
	}

	public BranchBlockGraphic(float x, float y, BranchFramePosition branchFramePos) {
		set(getSprite(branchFramePos), x, y);
	}

	private Sprite getSprite(BranchFramePosition branchFramePosition) {
		TextureRegion[] textureRegions = TextureHelper.getInstance().getTextureRegions(TextureId.BRANCH);
		return new Sprite(textureRegions[branchFramePosition.ordinal()]);
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
