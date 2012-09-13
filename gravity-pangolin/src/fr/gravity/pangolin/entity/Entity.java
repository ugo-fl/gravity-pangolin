package fr.gravity.pangolin.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.ScreenAbstract;
import fr.gravity.pangolin.util.GameUtil;

public abstract class Entity extends Sprite {

	public void init(TextureRegion frame, float x, float y) {
		ScreenAbstract screen = GameUtil.getScreen();
		
		setTexture(frame.getTexture());
		setSize(getWidth() / screen.getPpuX(), getHeight() / screen.getPpuY());
		setX(x);
		setY(y);
//		setPosition(-screen.getWidth() / 2 + x, -screen.getHeight() / 2 + y);
	}
	
	public void setX(float x) {
		super.setX(GameUtil.projectCoordinateX(x));
	}
	
	public void setY(float y) {
		super.setY(GameUtil.projectCoordinateY(y));
	}
	
	public abstract boolean collides();
}
