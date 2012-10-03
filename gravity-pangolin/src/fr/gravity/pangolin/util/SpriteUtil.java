package fr.gravity.pangolin.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.entity.EntityGraphic;
import fr.gravity.pangolin.screen.AbstractScreen;

public class SpriteUtil {

	public static Sprite generateSprite(TextureRegion frame, float x, float y, boolean rotate) {
		AbstractScreen screen = GameUtil.getScreen();
		
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
		AbstractScreen screen = GameUtil.getScreen();
		
		x = GameUtil.projectCoordinateX(x / screen.getPpuX()); 
		y = -GameUtil.projectCoordinateY(y / screen.getPpuY());
		
		if (x >= entityGraphic.x && x <= entityGraphic.x + entityGraphic.width)
			if (y >= entityGraphic.y && y <= entityGraphic.y + entityGraphic.height)
			return true;
		return false;
	}
	
}
