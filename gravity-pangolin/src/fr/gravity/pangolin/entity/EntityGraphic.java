package fr.gravity.pangolin.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.gravity.pangolin.ScreenAbstract;
import fr.gravity.pangolin.util.GameUtil;

public abstract class EntityGraphic extends Sprite {
	
	public void init(TextureRegion textureRegion, float x, float y) {
		ScreenAbstract screen = GameUtil.getScreen();
		
		setRegion(textureRegion);
		setSize(textureRegion.getRegionWidth() / screen.getPpuX(), textureRegion.getRegionHeight() / screen.getPpuY());
		setRegionWidth(textureRegion.getRegionWidth());
		setRegionHeight(textureRegion.getRegionHeight());
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
