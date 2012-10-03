package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.BranchBlockGraphic;
import fr.gravity.pangolin.entity.graphic.BranchBlockGraphic.BranchFramePosition;

public class BranchBlock extends Entity {

	public BranchBlock(float x, float y, BranchFramePosition branchFramePos) {
		entityGraphic = new BranchBlockGraphic(x, y, branchFramePos);
		addActor(entityGraphic);
	}

//	@Override
//	public void draw(SpriteBatch spriteBatch) {
//		entityGraphic.draw(spriteBatch);
//	}

	@Override
	public void touchDown() {
		
	}

	@Override
	public void touchUp() {
		
	}

//	@Override
//	public void draw(SpriteBatch batch, float parentAlpha) {
//		entityGraphic.draw(batch);
//	}

	@Override
	public Entity collides() {
		return this;
	}
	
	@Override
	public Actor hit(float x, float y) {
		return null;
	}

//	@Override
//	public Rectangle getBoundingRectangle() {
//		float gapX = 0F;
//		float gapWidth = 0F;
//		if (branchFramePos == BranchFramePosition.START) {
//			gapX = -0.6F;
//			gapWidth = 0.6F;
//		}
//		else if (branchFramePos == BranchFramePosition.END) {
//			gapWidth = 0.6F;
//		}
//		return new Rectangle(gapX, 0, 1F + gapWidth, 1.3F);
//	}
	
}
