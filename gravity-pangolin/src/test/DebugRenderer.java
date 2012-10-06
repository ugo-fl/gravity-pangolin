package test;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef.JointType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape.Type;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PulleyJoint;

import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;
import fr.gravity.pangolin.screen.TestBox2DScreen;

public class DebugRenderer {

	private final Color DEFAULT_COLOR = new Color(0.5f, 0.5f, 0.9f, 1);

	private final Color JOINT_COLOR = new Color(1f, 1f, 1f, 1);

	private SpriteBatch batch;

	protected ShapeRenderer renderer;

	/** vertices for polygon rendering **/
	private static Vector2[] vertices = new Vector2[1000];

	private TextureRegion region = TextureHelper.getInstance().getTextureRegions(TextureId.BRANCH)[0];

	public DebugRenderer() {
		this.batch = new SpriteBatch();
		renderer = new ShapeRenderer();

		// initialize vertices array
		for (int i = 0; i < vertices.length; i++)
			vertices[i] = new Vector2();
	}

	public void render(World world, Matrix4 projection) {

		batch.setProjectionMatrix(projection);
		renderer.setProjectionMatrix(projection);

		for (Iterator<Body> iter = world.getBodies(); iter.hasNext();) {
			Body body = iter.next();

			List<Fixture> fixtures = body.getFixtureList();
			for (int i = 0; i < fixtures.size(); i++) {
				Fixture fixture = fixtures.get(i);
				Transform transform = body.getTransform();

				renderer.begin(ShapeType.Line);
				drawShape(fixture, transform, DEFAULT_COLOR);
				renderer.end();
			}
		}

		renderer.begin(ShapeType.Line);
		for (Iterator<Joint> iter = world.getJoints(); iter.hasNext();) {
			Joint joint = iter.next();
			drawJoint(joint);
		}
		renderer.end();
	}

	private static Vector2 t = new Vector2();
	private static Vector2 axis = new Vector2();

	private void drawShape(Fixture fixture, Transform transform, Color color) {
		if (fixture.getType() == Type.Circle) {
			CircleShape circle = (CircleShape) fixture.getShape();
			t.set(circle.getPosition());
			transform.mul(t);
			drawSolidCircle(t, circle.getRadius(), axis.set(transform.vals[Transform.COS], transform.vals[Transform.SIN]), color);
		}

		if (fixture.getType() == Type.Edge) {
			EdgeShape edge = (EdgeShape) fixture.getShape();
			edge.getVertex1(vertices[0]);
			edge.getVertex2(vertices[1]);
			transform.mul(vertices[0]);
			transform.mul(vertices[1]);
			drawSolidPolygon(vertices, 2, color);
		}

		if (fixture.getType() == Type.Polygon) {
			PolygonShape chain = (PolygonShape) fixture.getShape();
			int vertexCount = chain.getVertexCount();
			for (int i = 0; i < vertexCount; i++) {
				chain.getVertex(i, vertices[i]);
				transform.mul(vertices[i]);
			}
			drawSolidPolygon(vertices, vertexCount, color);

		}

		if (fixture.getType() == Type.Chain) {
			ChainShape chain = (ChainShape) fixture.getShape();
			int vertexCount = chain.getVertexCount();
			for (int i = 0; i < vertexCount; i++) {
				chain.getVertex(i, vertices[i]);
				transform.mul(vertices[i]);
			}
			drawSolidPolygon(vertices, vertexCount, color);
		}
	}

	private final Vector2 f = new Vector2();
	private final Vector2 v = new Vector2();
	private final Vector2 lv = new Vector2();

	private void drawSolidCircle(Vector2 center, float radius, Vector2 axis, Color color) {
		float angle = 0;
		float angleInc = 2 * (float) Math.PI / 20;
		renderer.setColor(color.r, color.g, color.b, color.a);
		for (int i = 0; i < 20; i++, angle += angleInc) {
			v.set((float) Math.cos(angle) * radius + center.x, (float) Math.sin(angle) * radius + center.y);
			if (i == 0) {
				lv.set(v);
				f.set(v);
				continue;
			}
			renderer.line(lv.x, lv.y, v.x, v.y);
			lv.set(v);
		}
		renderer.line(f.x, f.y, lv.x, lv.y);
		renderer.line(center.x, center.y, 0, center.x + axis.x * radius, center.y + axis.y * radius, 0);
	}

	private void drawSolidPolygon(Vector2[] vertices, int vertexCount, Color color) {
		renderer.setColor(color.r, color.g, color.b, color.a);
		for (int i = 0; i < vertexCount; i++) {
			Vector2 v = vertices[i];
			if (i == 0) {
				lv.set(v);
				f.set(v);
				continue;
			}
			renderer.line(lv.x, lv.y, v.x, v.y);
			lv.set(v);
		}
		renderer.line(f.x, f.y, lv.x, lv.y);

		Texture texture = region.getTexture();
		texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

	}

	private void drawJoint(Joint joint) {
		Body bodyA = joint.getBodyA();
		Body bodyB = joint.getBodyB();
		Transform xf1 = bodyA.getTransform();
		Transform xf2 = bodyB.getTransform();

		Vector2 x1 = xf1.getPosition();
		Vector2 x2 = xf2.getPosition();
		Vector2 p1 = joint.getAnchorA();
		Vector2 p2 = joint.getAnchorB();

		if (joint.getType() == JointType.DistanceJoint) {
			drawSegment(p1, p2, JOINT_COLOR);
		} else if (joint.getType() == JointType.PulleyJoint) {
			PulleyJoint pulley = (PulleyJoint) joint;
			Vector2 s1 = pulley.getGroundAnchorA();
			Vector2 s2 = pulley.getGroundAnchorB();
			drawSegment(s1, p1, JOINT_COLOR);
			drawSegment(s2, p2, JOINT_COLOR);
			drawSegment(s1, s2, JOINT_COLOR);
		} else if (joint.getType() == JointType.MouseJoint) {
			drawSegment(joint.getAnchorA(), joint.getAnchorB(), JOINT_COLOR);
		} else {
			drawSegment(x1, p1, JOINT_COLOR);
			drawSegment(p1, p2, JOINT_COLOR);
			drawSegment(x2, p2, JOINT_COLOR);
		}
	}

	private void drawSegment(Vector2 x1, Vector2 x2, Color color) {
		renderer.setColor(color);
		renderer.line(x1.x, x1.y, x2.x, x2.y);
	}

	public void render(Body pangolinBody, Vector2 pangolinBodyOrigin, Sprite pangolinSprite) {
		Vector2 bottlePos = pangolinBody.getPosition().sub(pangolinBodyOrigin);

		pangolinSprite.setPosition(bottlePos.x, bottlePos.y);
		pangolinSprite.setOrigin(pangolinBodyOrigin.x, pangolinBodyOrigin.y);
		pangolinSprite.setRotation(pangolinBody.getAngle() * MathUtils.radiansToDegrees);
		pangolinSprite.setSize(TestBox2DScreen.PANGOLIN_WIDTH, TestBox2DScreen.PANGOLIN_WIDTH * pangolinSprite.getHeight() / pangolinSprite.getWidth());

		batch.begin();
		pangolinSprite.draw(batch);
		batch.end();

	}
}
