package fr.gravity.pangolin.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.entity.graphic.EntityGraphic;
import fr.gravity.pangolin.screen.IScreen;

public class SpriteUtil {

	public static Sprite generateSprite(TextureRegion frame, float x, float y, boolean rotate, IScreen screen) {
//		IScreen screen = (IScreen) GameUtil.getScreen();
		
		Sprite sprite = new Sprite(frame);
		sprite.setSize(sprite.getWidth() / screen.getPpuX(), sprite.getHeight() / screen.getPpuY());
		sprite.setPosition(x, y);
		if (rotate)
			sprite.rotate90(true);
		return sprite;
	}
	
	public static void rotate(Sprite sprite, boolean clockWise) {
		sprite.rotate90(clockWise);
		
		// Inverts width and height
		float width = sprite.getWidth();
		float height = sprite.getHeight();
		sprite.setSize(height, width);
	}
	
	public static boolean isTouched(EntityGraphic entityGraphic, float x, float y) {
		if (!(GameUtil.getScreen() instanceof IScreen))
			return false;
		
		IScreen screen = (IScreen) GameUtil.getScreen();
		
//		System.out.println("BEFORE PROJECT (" + entityGraphic + ") " + x + ", " + y);
//		x = GameUtil.projectCoordinateX(x / screen.getPpuX()); 
//		y = -GameUtil.projectCoordinateY(y / screen.getPpuY());
//		System.out.println("AFTER PROJECT (" + entityGraphic + ") " + x + ", " + y);
		
		x *= (screen.getWidth() / Gdx.graphics.getWidth());
		y = screen.getHeight() - (y * (screen.getHeight() / Gdx.graphics.getHeight()));
		
		if (x >= entityGraphic.getX() && x <= entityGraphic.getX() + entityGraphic.getWidth())
			if (y >= entityGraphic.getY() && y <= entityGraphic.getY() + entityGraphic.getHeight())
			return true;
		return false;
	}
	
}
