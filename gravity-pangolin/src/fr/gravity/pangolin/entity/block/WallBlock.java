package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.WallBlockGraphic;

public class WallBlock extends Entity {

	public WallBlock(World world, float x, float y) {
		super(world, x, y, 1);
	}

	@Override
	protected void createBody(World world, float x, float y) {
		EdgeShape edgeShape = new EdgeShape();
	    BodyDef bd = new BodyDef();
	    bd.position.set(x, y);
	    bd.type = BodyType.StaticBody;
	    
	    FixtureDef def = new FixtureDef();
	    def.shape = edgeShape;
	    def.density = 1;
	    
	    body = world.createBody(bd);
	    
	    // Down border
	    edgeShape.set(0, 0, 1, 0);
	    body.createFixture(def);
	    // Up border
	    edgeShape.set(0, 1, 1, 1);
	    body.createFixture(def);
	    // Left border
	    edgeShape.set(0, 0, 0, 1);
	    body.createFixture(def);
	    // Right border
	    edgeShape.set(1, 0, 1, 1);
	    body.createFixture(def);
	    
	    origin = new Vector2(0, 0);
	}
	
	@Override
	public void createGraphic(float x, float y) {
		entityGraphic = new WallBlockGraphic(x, y);
	}

	@Override
	public void touchDown() {

	}

	@Override
	public void touchUp() {
	}

//	@Override
//	public Entity collides() {
//		return this;
//	}

	@Override
	public Actor hit(float x, float y) {
		return null;
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
