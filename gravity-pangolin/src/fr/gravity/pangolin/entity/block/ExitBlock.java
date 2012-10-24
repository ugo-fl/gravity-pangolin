package fr.gravity.pangolin.entity.block;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.graphic.ExitBlockGraphic;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.world.GravityPangolinWorld;

public class ExitBlock extends Entity {

	private Direction direction = Direction.DOWN;
	
	public ExitBlock(GravityPangolinWorld gravityPangolinWorld, float x, float y, Direction direction) {
		super(gravityPangolinWorld, 1);
		this.direction = direction;

		createGraphic(x, y);
		createBody(gravityPangolinWorld.getWorld(), x, y);
	}

	public void createGraphic(float x, float y) {
		entityGraphic = new ExitBlockGraphic(x, y, direction);
	}

	protected void createBody(World world, float x, float y) {
		EdgeShape edgeShape = new EdgeShape();
		BodyDef bd = new BodyDef();
		bd.type = BodyType.KinematicBody;
		bd.position.set(x, y);

		FixtureDef def = new FixtureDef();
		def.shape = edgeShape;
		def.density = 1;
		def.filter.groupIndex = 99;

		edgeShape.set(0, 0, 1, 0);
		if (direction == Direction.UP) {
			bd.position.set(x, y + 0.75F);
			edgeShape.set(0, 0.25F, 1, 0.25F);
		} else if (direction == Direction.LEFT)
			edgeShape.set(0, 0, 0, 1);
		else if (direction == Direction.RIGHT) {
			edgeShape.set(0, 0, 0, 1);
			bd.position.set(x + 1F, y);
		}

		body = world.createBody(bd);
		Fixture fixture = body.createFixture(def);
		fixture.setUserData(this);

		origin = new Vector2(0, 0);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
	}

	@Override
	public void touchDown() {
	}

	@Override
	public void touchUp() {
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void beginContact(Object entity, Fixture fixture) {
		if (entity instanceof Pangolin) {
			if (direction == gravityPangolinWorld.getGravity().direction)
				GravityPangolinGame.getInstance().nextStage();
		}
	}

	@Override
	public void endContact(Object entity, Fixture fixture) {
		// TODO Auto-generated method stub

	}

}
