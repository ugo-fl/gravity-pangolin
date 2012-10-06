package fr.gravity.pangolin.screen;

import test.DebugRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.tablelayout.Table;
import com.badlogic.gdx.utils.TimeUtils;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.block.ExitBlock;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.game.CustomStage;
import fr.gravity.pangolin.game.GravityPangolinGame;
import fr.gravity.pangolin.helper.TextureHelper;
import fr.gravity.pangolin.helper.TextureHelper.TextureId;
import fr.gravity.pangolin.util.GameUtil;
import fr.gravity.pangolin.world.PangolinWorld;

public abstract class AbstractScreen implements Screen, InputProcessor {

	// Options
	public static final boolean DEBUG_ACTIVATED = true;
	public static final int GAME_VIEWPORT_WIDTH = 480, GAME_VIEWPORT_HEIGHT = 320;

	// Game logic
	protected final GravityPangolinGame game;
	protected final PangolinWorld pangolinWorld;
	protected CustomStage stage;
	protected OrthographicCamera camera;

	// Display
	private SpriteBatch batch;
	private DebugRenderer debugRenderer;
	private BitmapFont font;
	private Skin skin;
	private TextureAtlas atlas;
	private Table table;

	protected float width;
	protected float height;

	public AbstractScreen(GravityPangolinGame game, PangolinWorld pangolinWorld, float sizeX, float sizeY) {
		this.game = game;
		this.pangolinWorld = pangolinWorld;
		width = sizeX;
		height = sizeY;
	}

	// Screen implementation

	@Override
	public void show() {
		Gdx.app.log(GravityPangolinGame.LOG, "Showing screen: " + getName());

		// setup the camera. In Box2D we operate on a
		// meter scale, pixels won't do it. So we use
		// an orthographic camera with a viewport of
		// 48 meters in width and 32 meters in height.
		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(false, width, height);
//		camera.position.set(0, 5, 0);
		
		batch = new SpriteBatch();
		stage = new CustomStage(game, width, height, true);
		stage.setCamera(camera);
		font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), false);
		font.setColor(new Color(1, 1, 0, 1));
		
		// Debug renderer
		debugRenderer = new DebugRenderer();

		// TEST
		world = pangolinWorld.getWorld();

		BodyDef bodyDef = new BodyDef();
		groundBody = world.createBody(bodyDef);

		// set the stage as the input processor
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(GravityPangolinGame.LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height);
	}

	@Override
	public void render(float delta) {
		// update the actors
		// stage.act(delta);

		// update the world with a fixed time step
		long startTime = TimeUtils.nanoTime();
		pangolinWorld.step(Gdx.app.getGraphics().getDeltaTime(), 3, 3);
		stage.act(delta);
		float updateTime = (TimeUtils.nanoTime() - startTime) / 1000000000.0f;

		startTime = TimeUtils.nanoTime();
		// clear the screen and setup the projection matrix
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();

		// draw the actors
		stage.draw();

		if (DEBUG_ACTIVATED)
			drawDebug(startTime, updateTime);

	}

	private void drawDebug(long startTime, float updateTime) {
		// render the world using the debug renderer
		debugRenderer.render(pangolinWorld.getWorld(), camera.combined);
		float renderTime = (TimeUtils.nanoTime() - startTime) / 1000000000.0f;

		drawGround(pangolinWorld.getWorld());

		// Sprite pangolinSprite = new
		// Sprite(TextureHelper.getInstance().getTextureRegions(TextureId.PANGOLIN)[0]);
		// debugRenderer.render(pangolinBody, pangolinBodyOrigin,
		// pangolinSprite);

		batch.begin();
		font.draw(batch, "fps:" + Gdx.graphics.getFramesPerSecond() + ", update: " + updateTime + ", render: " + renderTime, 0, 20);
		batch.end();
	}

	private void drawGround(World world) {
		EdgeShape shape = new EdgeShape();
		shape.set(new Vector2(0, 0), new Vector2(width, 0));

		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.friction = 0.3f;

		BodyDef bd = new BodyDef();
		Body ground = world.createBody(bd);
		ground.createFixture(fd);
		shape.dispose();
	}

	// private void drawDebugView() {
	// ShapeRenderer debugRenderer = new ShapeRenderer();
	//
	// debugRenderer.setProjectionMatrix(stage.getCamera().combined);
	//
	// if (pangolinWorld == null)
	// return;
	//
	// Pangolin pangolin = pangolinWorld.getPangolin();
	//
	// for (Actor actor : stage.getActors()) {
	// if (!(actor instanceof Entity))
	// continue;
	//
	// Rectangle bounds = ((Entity) actor).getBoundingRectangle();
	//
	// // Draws actors
	// if (actor instanceof ExitBlock) {
	// // BoundingBox bb = ((ExitBlock)
	// // actor).getEntityGraphic().getBoundingBox();
	// // debugRenderer.begin(ShapeType.Line);
	// } else {
	// debugRenderer.begin(ShapeType.Rectangle);
	// debugRenderer.setColor(new Color(0, 1, 0, 1));
	// debugRenderer.rect(bounds.getX(), bounds.getY(), bounds.getWidth(),
	// bounds.getHeight());
	// debugRenderer.end();
	// }
	//
	// // Draws collision points
	// debugRenderer.begin(ShapeType.Circle);
	// debugRenderer.setColor(new Color(1, 0, 0, 0));
	// debugRenderer.circle(bounds.getX(), bounds.getY(), 2.5F);
	// debugRenderer.setColor(new Color(25, 85, 26, 3));
	// debugRenderer.circle(bounds.getX(), bounds.getY() + bounds.getHeight(),
	// 2.5F);
	// debugRenderer.circle(bounds.getX(), bounds.getY() - pangolin.getHeight(),
	// 2.5F);
	// debugRenderer.setColor(new Color(1, 1, 0, 1));
	// debugRenderer.circle(bounds.getX() + bounds.getWidth(), bounds.getY(),
	// 2.5F);
	// debugRenderer.circle(bounds.getX() - pangolin.getWidth(), bounds.getY(),
	// 2.5F);
	// debugRenderer.end();
	// }
	// }

	@Override
	public void hide() {
		Gdx.app.log(GravityPangolinGame.LOG, "Hiding screen: " + getName());

		// dispose the screen when leaving the screen;
		// note that the dipose() method is not called automatically by the
		// framework, so we must figure out when it's appropriate to call it
		dispose();
	}

	@Override
	public void pause() {
		Gdx.app.log(GravityPangolinGame.LOG, "Pausing screen: " + getName());
	}

	@Override
	public void resume() {
		Gdx.app.log(GravityPangolinGame.LOG, "Resuming screen: " + getName());
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void dispose() {
		Gdx.app.log(GravityPangolinGame.LOG, "Disposing screen: " + getName());

		// the following call disposes the screen's stage, but on my computer it
		// crashes the game so I commented it out; more info can be found at:
		// http://www.badlogicgames.com/forum/viewtopic.php?f=11&t=3624
		// stage.dispose();

		// as the collaborators are lazily loaded, they may be null
		if (font != null)
			font.dispose();
		// if (batch != null)
		// batch.dispose();
		if (skin != null)
			skin.dispose();
		if (atlas != null)
			atlas.dispose();
	}

	protected String getName() {
		return getClass().getSimpleName();
	}

	protected boolean isGameScreen() {
		return false;
	}

	// Lazily loaded collaborators

	public BitmapFont getFont() {
		if (font == null) {
			font = new BitmapFont();
		}
		return font;
	}

	public Skin getSkin() {
		// Disabled because does not seem to work when hiding/showing a screen
		// if (skin == null) {
		FileHandle skinFile = Gdx.files.internal("skin/uiskin.json");
		skin = new Skin(skinFile);
		// }
		return skin;
	}

	protected Table getTable() {
		if (table == null) {
			table = new Table(getSkin());
			table.setFillParent(true);
			stage.addActor(table);
		}
		return table;
	}

	protected void loadBackButton(ClickListener clickListener) {
		TextButton backButton = new TextButton(getSkin());
		backButton.setText("Back");
		backButton.x = 400;
		backButton.y = GameUtil.getScreen().getHeight() - 60;
		backButton.width = 50;
		backButton.height = 50;
		backButton.setClickListener(clickListener);
		stage.addActor(backButton);
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getPpuX() {
		if (pangolinWorld == null)
			return 1;
		float sizeX = pangolinWorld.getSizeX();
		return width / sizeX;
	}

	public float getPpuY() {
		if (pangolinWorld == null)
			return 1;
		float sizeY = pangolinWorld.getSizeY();
		return height / sizeY;
	}

	// TEST

	protected Body hitBody = null;
	protected World world;
	protected Body groundBody;
	protected MouseJoint mouseJoint = null;

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
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
