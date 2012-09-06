package fr.gravity.pangolin;

import com.badlogic.gdx.Game;


public class GravityPangolinGame extends Game {

	@Override
	public void create() {
		setScreen(new GravityPangolinScreen());
	}

}
