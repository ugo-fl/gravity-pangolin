package fr.gravity.pangolin.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.ScreenAbstract;

public class SpriteUtil {

	public static Sprite generateSprite(TextureRegion frame, float x, float y, boolean rotate) {
		ScreenAbstract screen = GameUtil.getScreen();
		
		Sprite sprite = new Sprite(frame);
		sprite.setSize(sprite.getWidth() / screen.getPpuX(), sprite.getHeight() / screen.getPpuY());
		sprite.setPosition(-screen.getWidth() / 2 + x, -screen.getHeight() / 2 + y);
		if (rotate)
			sprite.rotate90(true);
		return sprite;
	}
	
	public static boolean isTouched(Sprite sprite, float x, float y) {
		ScreenAbstract screen = GameUtil.getScreen();
		
		x = -screen.getWidth() / 2 + (x / screen.getPpuX());
		y = screen.getHeight() / 2 - (y / screen.getPpuY());
		if (x >= sprite.getX() && x <= sprite.getX() + sprite.getWidth())
			if (y >= sprite.getY() && y <= sprite.getY() + sprite.getHeight())
			return true;
		return false;
	}
}
