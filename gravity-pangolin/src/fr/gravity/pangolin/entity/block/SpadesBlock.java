package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.SpadesBlockGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class SpadesBlock extends Entity {

	private static final float WIDTH = 1.5F;
	private static final float HEIGHT = 0.20F;

	private Direction direction;
	
	public SpadesBlock(GravityPangolinWorld gravityPangolinWorld, float x, float y, Direction direction) {
		super(gravityPangolinWorld, 1.5F);
		
		this.direction = direction;
		
		createBody(gravityPangolinWorld.getWorld(), x, y);
		createGraphic(x, y);
	}

	private void createBody(World world, float x, float y) {
	    BodyDef bd = new BodyDef();
	    bd.position.set(x, y);
	    bd.type = BodyType.StaticBody;
	    bd.angle = (float) Math.toRadians(direction.angle);
	    
	    PolygonShape polygonShape = new PolygonShape();
	    
	    polygonShape.setAsBox(WIDTH / 2, HEIGHT / 2);
	    
	    FixtureDef def = new FixtureDef();
	    def.shape = polygonShape;
	    def.density = 1;
	    def.friction = 0;
	    
	    body = world.createBody(bd);
	    Fixture fixture = body.createFixture(def);
	    fixture.setUserData(this);
	    
	    origin = new Vector2(WIDTH / 2, HEIGHT / 2);
	}
	
	private void createGraphic(float x, float y) {
		entityGraphic = new SpadesBlockGraphic(x, y);
	}

	@Override
	public void beginContact(Object entity, Fixture fixture) {
		if (entity instanceof Pangolin)
			gravityPangolinWorld.getPangolin().die();
	}

	@Override
	public void endContact(Object entity, Fixture fixture) {
		// TODO Auto-generated method stub

	}

	@Override
	public void touchDown() {
		System.out.println("Spades touched down");

	}

	@Override
	public void touchUp() {
		// TODO Auto-generated method stub

	}

}
