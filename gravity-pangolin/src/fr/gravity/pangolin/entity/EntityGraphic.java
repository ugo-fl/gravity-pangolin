package fr.gravity.pangolin.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.ScreenAbstract;
import fr.gravity.pangolin.util.GameUtil;

public abstract class EntityGraphic extends Sprite {
	
	public void init(TextureRegion frame, float x, float y) {
		ScreenAbstract screen = GameUtil.getScreen();
		
		Texture texture = frame.getTexture(); 
		
		setTexture(texture);
		setSize(texture.getWidth() / screen.getPpuX(), texture.getHeight() / screen.getPpuY());
		setPosition(x, y);
	}
	
	public void setPosition(float x, float y) {
		setX(x);
		setY(y);
	}
	
	public void setX(float x) {
		super.setX(GameUtil.projectCoordinateX(x));
	}
	
	public void setY(float y) {
		super.setY(GameUtil.projectCoordinateY(y));
	}
	
}
