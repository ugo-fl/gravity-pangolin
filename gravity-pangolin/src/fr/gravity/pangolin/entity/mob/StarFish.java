package fr.gravity.pangolin.entity.mob;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import fr.gravity.pangolin.constant.FilterMask;
import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.StarFishGraphic;
import fr.gravity.pangolin.util.CountDown;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class StarFish extends Entity {

	private static final float SIZE = 0.5F;
	
	private CountDown shootCountDown = new CountDown(5000);
	
	public StarFish(GravityPangolinWorld gravityPangolinWorld, float x, float y) {
		super(gravityPangolinWorld, 1);
		this.x = x;
		this.y = y;
		createBody(gravityPangolinWorld.getWorld(), x, y);
		createGraphic(x, y);
		shootCountDown.start();
	}

	public void createBody(World world, float x, float y) {
	    BodyDef bd = new BodyDef();
	    bd.position.set(x, y);
	    bd.type = BodyType.KinematicBody;
	    
	    PolygonShape polygonShape = new PolygonShape();
	    polygonShape.setAsBox(SIZE, SIZE);
	    
	    FixtureDef def = new FixtureDef();
	    def.shape = polygonShape;
	    def.density = 1;
	    def.friction = 0;
	    def.filter.categoryBits = FilterMask.STAR_FISH;
	    def.isSensor = true;
	    
	    body = world.createBody(bd);
	    body.createFixture(def);
	    
	    origin = new Vector2(SIZE, SIZE);
	}
	
	public void createGraphic(float x, float y) {
		entityGraphic = new StarFishGraphic(x, y);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		if (!shootCountDown.isFinished())
			return ;
		shootCountDown.start();
		gravityPangolinWorld.addEntity(new Projectile(gravityPangolinWorld, x, y));
	}
	
	@Override
	public void beginContact(Object entity, Fixture fixture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endContact(Object entity, Fixture fixture) {
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
