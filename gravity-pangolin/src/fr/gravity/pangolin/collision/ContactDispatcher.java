package fr.gravity.pangolin.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import fr.gravity.pangolin.entity.Entity;

public class ContactDispatcher implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Object userDataA = contact.getFixtureA().getUserData();
		Object userDataB = contact.getFixtureB().getUserData();
		
		if (userDataA instanceof Entity)
			((Entity) userDataA).beginContact(userDataB);
		if (userDataB instanceof Entity)
			((Entity) userDataB).beginContact(userDataA);
	}

	@Override
	public void endContact(Contact contact) {
		Object userDataA = contact.getFixtureA().getUserData();
		Object userDataB = contact.getFixtureB().getUserData();
		
		if (userDataA instanceof Entity)
			((Entity) userDataA).endContact(userDataB);
		if (userDataB instanceof Entity)
			((Entity) userDataB).endContact(userDataA);
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}
