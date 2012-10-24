package fr.gravity.pangolin.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.block.GravityChangerBlock;

public class ContactDispatcher implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Object userDataA = contact.getFixtureA().getUserData();
		Object userDataB = contact.getFixtureB().getUserData();
		
		if (userDataA instanceof Entity)
			((Entity) userDataA).beginContact(userDataB, contact.getFixtureB());
		if (userDataB instanceof Entity)
			((Entity) userDataB).beginContact(userDataA, contact.getFixtureA());
	}

	@Override
	public void endContact(Contact contact) {
		Object userDataA = contact.getFixtureA().getUserData();
		Object userDataB = contact.getFixtureB().getUserData();
		
		if (userDataA instanceof Entity)
			((Entity) userDataA).endContact(userDataB, contact.getFixtureA());
		if (userDataB instanceof Entity)
			((Entity) userDataB).endContact(userDataA, contact.getFixtureB());
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}
