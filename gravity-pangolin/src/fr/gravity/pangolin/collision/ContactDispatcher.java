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
			((Entity) userDataA).collidesWith(userDataB);
	}

	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}
