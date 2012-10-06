package fr.gravity.pangolin.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CustomStage extends Stage {

	private GravityPangolinGame gravityPangolinGame;
	
	public CustomStage(GravityPangolinGame gravityPangolinGame, float width, float height, boolean stretch) {
		super(width, height, stretch);
		this.gravityPangolinGame = gravityPangolinGame;
	}
	
}
