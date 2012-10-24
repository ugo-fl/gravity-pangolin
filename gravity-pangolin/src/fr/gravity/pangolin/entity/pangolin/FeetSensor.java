package fr.gravity.pangolin.entity.pangolin;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.physics.box2d.Fixture;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.block.GravityChangerBlock;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class FeetSensor extends Entity {

	private Pangolin pangolin;
	
	// The fixtures that come in contact with the feet
	private List<Entity> inContactEntities = new ArrayList<Entity>();

	public FeetSensor(GravityPangolinWorld gravityPangolinWorld, Pangolin pangolin) {
		super(gravityPangolinWorld, 1);
		this.pangolin = pangolin;
	}

	public boolean isInContact() {
		return inContactEntities.size() > 0;
	}

	@Override
	public void beginContact(Object entity, Fixture fixture) {
		if (entity instanceof GravityChangerBlock)
			return;
		inContactEntities.add((Entity) entity);
	}

	@Override
	public void endContact(Object entity, Fixture fixture) {
		inContactEntities.remove((Entity) entity);
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
