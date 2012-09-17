package fr.gravity.pangolin.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.screen.ScreenAbstract;

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
		
//		System.out.println("TOUCHED X: " + GameUtil.projectCoordinateX(x / screen.getPpuX()) + "(" + x + ")");
//		System.out.println("TOUCHED Y: " + GameUtil.projectCoordinateY(y / screen.getPpuY()) + "(" + y + ")");
		x = GameUtil.projectCoordinateX(x / screen.getPpuX()); 
		y = -GameUtil.projectCoordinateY(y / screen.getPpuY());
		
//		System.out.println("SPRITE X:" + sprite.getX() + " WIDTH:" + sprite.getWidth());
//		System.out.println("SPRITE Y:" + sprite.getY() + " HEIGHT:" + sprite.getHeight());
//		System.out.println("-----");
		
		if (x >= sprite.getX() && x <= sprite.getX() + sprite.getWidth())
			if (y >= sprite.getY() && y <= sprite.getY() + sprite.getHeight())
			return true;
		return false;
	}
}
