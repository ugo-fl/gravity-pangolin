package fr.gravity.pangolin;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import fr.gravity.pangolin.entity.Pangolin;
import fr.gravity.pangolin.entity.block.Block;
import fr.gravity.pangolin.entity.block.ExitBlock;
import fr.gravity.pangolin.entity.block.GravityChangerBlock;

public class WorldRenderer {

	private boolean debug;

	private PangolinWorld world;
	private Pangolin pangolin;
	private Background background;

	private OrthographicCamera cam;
	private SpriteBatch spriteBatch;

	/** for debug rendering **/
	private ShapeRenderer debugRenderer = new ShapeRenderer();

	private int screenWidth;
	private int screenHeight;
//	private float ppuX;
//	private float ppuY;

	public WorldRenderer(PangolinWorld world, GravityPangolinScreen screen, boolean debug) {
		this.world = world;
		this.pangolin = world.getPangolin();
		this.background = world.getBackground();
		this.debug = debug;
		
		this.spriteBatch = screen.getSpriteBatch();
		this.cam = screen.getCamera();
//		this.width = world.getSizeX();
//		this.height = world.getSizeY();
//		this.ppuX = (float) Gdx.graphics.getWidth() / width;
//		this.ppuY = (float) Gdx.graphics.getHeight() / height;

//		cam = new OrthographicCamera(this.width, this.height);
//		cam.setToOrtho(true, this.width, this.height);
//		cam.update();
//		spriteBatch = new SpriteBatch();
//		spriteBatch.setProjectionMatrix(cam.combined);
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
		spriteBatch.draw(background.getTextureRegion(), 0, 0, screenWidth, screenHeight);
		/* DRAW BLOCKS */
		Sprite exitBlock = null;
		for (Sprite block : world.getBlocks()) {
			if (block instanceof ExitBlock) {
				exitBlock = block;
				continue;
			}
			block.draw(spriteBatch);
//			drawSprite(block);
		}
		exitBlock.draw(spriteBatch);
//		drawSprite(exitBlock);
		/* DRAW PANGOLIN */
		pangolin.draw(spriteBatch);
	}

//	private void drawSprite(Sprite sprite) {
//		spriteBatch.draw(sprite.getTextureRegion(), sprite.getPosition().x + sprite.getBoundingRectangle().x,
//				sprite.getPosition().y + sprite.getBoundingRectangle().y, sprite.getBoundingRectangle().getWidth(), sprite
//						.getBoundingRectangle().getHeight());
//	}

	private void drawDebug() {
		debugRenderer.setProjectionMatrix(cam.combined);
		debugRenderer.begin(ShapeType.Rectangle);

		// render blocks
		for (Sprite block : world.getBlocks()) {
			Rectangle rect = block.getBoundingRectangle();
			float x1 = block.getX() + rect.x;
			float y1 = block.getY() + rect.y;
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

	private Color getBlockColor(Sprite block) {
		if (block instanceof GravityChangerBlock)
			return new Color(0, 0, 1, 1);
		return new Color(1, 0, 0, 1);
	}
}
