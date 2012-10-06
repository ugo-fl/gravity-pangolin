package fr.gravity.pangolin.entity.block;

import test.BodyEditorLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.BranchBlockGraphic;
import fr.gravity.pangolin.util.GameUtil;

public class BranchBlock extends Entity {

	public BranchBlock(World world, float x, float y) {
		super(world, x, y, 1);
	}

	@Override
	public void createGraphic(float x, float y) {
		entityGraphic = new BranchBlockGraphic(x, y);
	}

	@Override
	public void touchDown() {

	}

	@Override
	public void touchUp() {

	}

	@Override
	public Entity collides() {
		return this;
	}

	@Override
	public Actor hit(float x, float y) {
		return null;
	}

	@Override
	protected String getBodyName() {
		return "wall_block";
	}

	// @Override
	// public Rectangle getBoundingRectangle() {
	// float gapX = 0F;
	// float gapWidth = 0F;
	// if (branchFramePos == BranchFramePosition.START) {
	// gapX = -0.6F;
	// gapWidth = 0.6F;
	// }
	// else if (branchFramePos == BranchFramePosition.END) {
	// gapWidth = 0.6F;
	// }
	// return new Rectangle(gapX, 0, 1F + gapWidth, 1.3F);
	// }

}
