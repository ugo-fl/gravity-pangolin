package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.physics.box2d.World;

import fr.gravity.pangolin.entity.Entity;

public class HarmfulBlock extends Entity {

	public HarmfulBlock(World world, float x, float y) {
		super(world, x, y, 1);
	}

	@Override
	public void createGraphic(float x, float y) {
	}

	@Override
	protected void createBody(World world, float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beginContact(Object entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Object entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub
		
	}

}
