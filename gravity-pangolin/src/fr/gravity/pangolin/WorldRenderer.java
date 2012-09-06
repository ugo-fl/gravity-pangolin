package fr.gravity.pangolin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import fr.gravity.pangolin.block.Block;
import fr.gravity.pangolin.block.GravityChanger;

public class WorldRenderer {

	private boolean debug;

	private PangolinWorld world;
	private Pangolin pangolin;
	private Background background;

	private OrthographicCamera cam;
	private SpriteBatch spriteBatch;

	/** Textures **/
	// private TextureRegion[] walkFrames;
	// private Animation walkAnimation;
	// private TextureRegion currentFrame;
	// private float stateTime = 0f;

	/** for debug rendering **/
	private ShapeRenderer debugRenderer = new ShapeRenderer();

	private int width;
	private int height;
	private float ppuX;
	private float ppuY;

	public WorldRenderer(PangolinWorld world, boolean debug) {
		this.world = world;
		this.pangolin = world.getPangolin();
		this.background = world.getBackground();
		this.debug = debug;
		this.width = world.getSizeX();
		this.height = world.getSizeY();
		this.ppuX = (float) Gdx.graphics.getWidth() / width;
		this.ppuY = (float) Gdx.graphics.getHeight() / height;

		cam = new OrthographicCamera(this.width, this.height);
		cam.setToOrtho(true, this.width, this.height);
		cam.update();
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(cam.combined);
	}

	public void render() {
		spriteBatch.begin();
		draw();
		spriteBatch.end();
		if (debug)
			drawDebug();
	}

	private void draw() {
		/* DRAW BACKGROUND */
		spriteBatch.draw(background.getTextureRegion(), 0, 0, width, height);
		/* DRAW PANGOLIN */
		spriteBatch.draw(pangolin.getCurrentFrame(), pangolin.getPosition().x, pangolin.getPosition().y,
				Pangolin.WIDTH, Pangolin.HEIGHT);
		/* DRAW BLOCKS */
		for (Block block : world.getBlocks()) {
			spriteBatch.draw(block.getTextureRegion(), block.getPosition().x + block.getTextureBounds().x,
					block.getPosition().y + block.getTextureBounds().y, block.getTextureBounds().getWidth(), block
							.getTextureBounds().getHeight());
		}
	}

	private void drawDebug() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);

		// render blocks
		for (Block block : world.getBlocks()) {
			Rectangle rect = block.getBounds();
			float x1 = block.getPosition().x + rect.x;
			float y1 = block.getPosition().y + rect.y;
			debugRenderer.setColor(getBlockColor(block));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}

		// render Pangolin
		Pangolin pangolin = world.getPangolin();
		Rectangle rect = pangolin.getBounds();
		float x1 = pangolin.getPosition().x;
		float y1 = pangolin.getPosition().y;
		debugRenderer.setColor(new Color(0, 1, 0, 1));
		debugRenderer.rect(x1, y1, rect.width, rect.height);
		debugRenderer.end();
	}

	private Color getBlockColor(Block block) {
		if (block instanceof GravityChanger)
			return new Color(0, 0, 1, 1);
		return new Color(1, 0, 0, 1);
	}
}
