package fr.gravity.pangolin.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class CustomStage extends Stage {

	private GravityPangolinGame gravityPangolinGame;
	
	public CustomStage(GravityPangolinGame gravityPangolinGame, float arg0, float arg1, boolean arg2) {
		super(arg0, arg1, arg2);
		this.gravityPangolinGame = gravityPangolinGame;
	}

}
