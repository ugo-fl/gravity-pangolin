package test;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.utils.TimeUtils;

import fr.gravity.pangolin.collision.BodyEditorLoader;
import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;

public class TestBox2DScreen implements Screen, InputProcessor {

	/** the camera **/
	protected OrthographicCamera camera;

	/** the renderer **/
	protected Box2DDebugRenderer renderer;

	SpriteBatch batch;
	BitmapFont font;

	/** our box2D world **/
	protected World world;

	/** ground body to connect the mouse joint to **/
	protected Body groundBody;

	/** our mouse joint **/
	protected MouseJoint mouseJoint = null;

	/** a hit body **/
	protected Body hitBody = null;

	/** temp vector **/
	protected Vector2 tmp = new Vector2();

	// TEST

	private DebugRenderer myRenderer;

	@Override
	public void render(float delta) {
		// update the world with a fixed time step
		long startTime = TimeUtils.nanoTime();
		world.step(Gdx.app.getGraphics().getDeltaTime(), 3, 3);
		float updateTime = (TimeUtils.nanoTime() - startTime) / 1000000000.0f;

		startTime = TimeUtils.nanoTime();
		// clear the screen and setup the projection matrix
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();

		// render the world using the debug renderer
		// renderer.render(world, camera.combined);
		float renderTime = (TimeUtils.nanoTime() - startTime) / 1000000000.0f;

		myRenderer.render(world, camera.combined);
		
//		Sprite pangolinSprite = new Sprite(TextureHelper.getInstance().getTextureRegions(TextureId.PANGOLIN)[0]);
//		myRenderer.render(pangolinBody, pangolinBodyOrigin, pangolinSprite);
		
		batch.begin();
		font.draw(batch, "fps:" + Gdx.graphics.getFramesPerSecond() + ", update: " + updateTime + ", render: " + renderTime, 0, 20);
		batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// setup the camera. In Box2D we operate on a
		// meter scale, pixels won't do it. So we use
		// an orthographic camera with a viewport of
		// 48 meters in width and 32 meters in height.
		// We also position the camera so that it
		// looks at (0,16) (that's where the middle of the
		// screen will be located).
		camera = new OrthographicCamera(48, 32);
		camera.position.set(0, 15, 0);

		// create the debug renderer
		renderer = new Box2DDebugRenderer();

		// create the world
		world = new World(new Vector2(0, -10), true);

		// we also need an invisible zero size ground body
		// to which we can connect the mouse joint
		BodyDef bodyDef = new BodyDef();
		groundBody = world.createBody(bodyDef);

		createWorld(world);

		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), false);

		// TEST
		myRenderer = new DebugRenderer();

		Gdx.input.setInputProcessor(this);
	}

	private void createWorld(World world2) {
		EdgeShape shape = new EdgeShape();
		shape.set(new Vector2(-40.0f, 0), new Vector2(40, 0));

		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.friction = 0.3f;

		BodyDef bd = new BodyDef();
		Body ground = world.createBody(bd);
		ground.createFixture(fd);
		shape.dispose();

		PolygonShape shape2 = new PolygonShape();
		shape2.setAsBox(1, 2f);

		BodyDef def = new BodyDef();
		def.position.y = 10;
		def.angle = (float) Math.toRadians(90);
		def.type = BodyType.DynamicBody;

		Body body = world.createBody(def);
		body.createFixture(shape2, 1);

		def = new BodyDef();
		def.position.x = 10;
		def.position.y = 10;
		def.angle = 0;
		def.type = BodyType.DynamicBody;

		body = world.createBody(def);
		body.createFixture(shape2, 1);

		shape2.dispose();

		// TRYING TO CREATE A BRANCH BLOCK
//		createPangolin();
	}

	// TextureWrapper tw;

	Body pangolinBody;
	Vector2 pangolinBodyOrigin;
	public static float PANGOLIN_WIDTH = 8;

	private void createPangolin() {
	    // 0. Create a loader for the file saved from the editor.
	    BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("images/sprite_pangolin.json"));
	 
	    // 1. Create a BodyDef, as usual.
	    BodyDef bd = new BodyDef();
	    bd.position.set(0, 0);
	    bd.type = BodyType.DynamicBody;
	 
	    // 2. Create a FixtureDef, as usual.
	    FixtureDef fd = new FixtureDef();
	    fd.density = 1;
	    fd.friction = 0.5f;
	    fd.restitution = 0.3f;
	 
	    // 3. Create a Body, as usual.
	    pangolinBody = world.createBody(bd);
	    
	    // 4. Create the body fixture automatically by using the loader.
	    loader.attachFixture(pangolinBody, "Pangolino", fd, PANGOLIN_WIDTH);
	    
	    pangolinBodyOrigin = loader.getOrigin("Pangolino", PANGOLIN_WIDTH).cpy();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	/**
	 * we instantiate this vector and the callback here so we don't irritate the
	 * GC
	 **/
	Vector3 testPoint = new Vector3();
	QueryCallback callback = new QueryCallback() {
		@Override
		public boolean reportFixture(Fixture fixture) {
			// if the hit point is inside the fixture of the body
			// we report it
			if (fixture.testPoint(testPoint.x, testPoint.y)) {
				hitBody = fixture.getBody();
				return false;
			} else
				return true;
		}
	};

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// translate the mouse coordinates to world coordinates
		camera.unproject(testPoint.set(x, y, 0));
		// ask the world which bodies are within the given
		// bounding box around the mouse pointer
		hitBody = null;
		world.QueryAABB(callback, testPoint.x - 0.0001f, testPoint.y - 0.0001f, testPoint.x + 0.0001f, testPoint.y + 0.0001f);

		if (hitBody == groundBody)
			hitBody = null;

		// ignore kinematic bodies, they don't work with the mouse joint
		if (hitBody != null && hitBody.getType() == BodyType.KinematicBody)
			return false;

		// if we hit something we create a new mouse joint
		// and attach it to the hit body.
		if (hitBody != null) {
			MouseJointDef def = new MouseJointDef();
			def.bodyA = groundBody;
			def.bodyB = hitBody;
			def.collideConnected = true;
			def.target.set(testPoint.x, testPoint.y);
			def.maxForce = 1000.0f * hitBody.getMass();

			mouseJoint = (MouseJoint) world.createJoint(def);
			hitBody.setAwake(true);
		}
		return false;
	}

	/** another temporary vector **/
	Vector2 target = new Vector2();

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// if a mouse joint exists we simply update
		// the target of the joint based on the new
		// mouse coordinates
		if (mouseJoint != null) {
			camera.unproject(testPoint.set(x, y, 0));
			mouseJoint.setTarget(target.set(testPoint.x, testPoint.y));
		}
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		// if a mouse joint exists we simply destroy it
		if (mouseJoint != null) {
			world.destroyJoint(mouseJoint);
			mouseJoint = null;
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
}
