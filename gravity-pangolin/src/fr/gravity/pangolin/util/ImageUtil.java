package fr.gravity.pangolin.util;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageUtil {

	public static void rotate90(Image image, boolean clockwise) {
		float angle = clockwise ? 270 : 90;
		
//		image.originX = image.width;
//		image.originX = image.height;
		image.rotation = 90;
		
//		image.originX = image.height / 2 + 2;
//		image.originY = image.height / 2 + 2;
		
		
//		image.height = image.width;
//		image.width = image.originX;
		
//		if (clockwise)
//			image.width = -image.width;
//		image.height = -image.height;
	}

}
