package fr.gravity.pangolin;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.Pangolin;
import fr.gravity.pangolin.util.Numbers;

public class CollisionHelper {

	private Pangolin pangolin;
	private Array<Entity> entities;

	private static CollisionHelper instance;

	private CollisionHelper(Pangolin pangolin, Array<Entity> entities) {
		this.pangolin = pangolin;
		this.entities = entities;
	}

	public static CollisionHelper getInstance(Pangolin pangolin, Array<Entity> entities) {
		if (instance == null)
			instance = new CollisionHelper(pangolin, entities);
		return instance;
	}

	public static CollisionHelper getInstance() {
		return instance;
	}

	public boolean collidesLeft(Entity entity) {
		if (entity == null)
			return false;
		if (Numbers.between(pangolin.getPosition().x, entity.getX(), entity.getY()
				+ entity.getBoundingRectangle().getWidth()))
			return true;
		return false;
	}

	public boolean collidesRight(Entity entity) {
		if (entity == null)
			return false;
		if (Numbers.between(pangolin.getPosition().x + pangolin.getBounds().getWidth(), entity.getOriginX(),
				entity.getX() + entity.getBoundingRectangle().getWidth()))
			return true;
		return false;
	}

	public boolean collidesUp(Entity entity) {
		if (entity == null)
			return false;
		if (Numbers.between(pangolin.getPosition().y, entity.getOriginY(), entity.getOriginY()
				+ entity.getBoundingRectangle().getHeight()))
			return true;
		return false;
	}

	public boolean collidesDown(Entity entity) {
		if (entity == null)
			return false;
		if (Numbers.between(pangolin.getPosition().y + pangolin.getBounds().getHeight(), entity.getRegionY(),
				entity.getRegionY() + entity.getBoundingRectangle().getHeight()))
			return true;
		return false;
	}

	/**
	 * Returns the Block the Pangolin collides with if and only if the block is
	 * Solid
	 * 
	 * @param block
	 * @param pangolin
	 * @return
	 */
	public Entity collides(Pangolin pangolin) {
		float pangolinPosX = pangolin.getPosition().x;
		float pangolinPosY = pangolin.getPosition().y;
		float pangolinWidth = pangolin.getBounds().getWidth();
		float pangolinHeight = pangolin.getBounds().getHeight();

		for (Entity entity : entities) {
			float blockPosX = entity.getOriginX();
			float blockPosY = entity.getY();
			float blockWidth = entity.getBoundingRectangle().getWidth();
			float blockHeight = entity.getBoundingRectangle().getHeight();

			if (Numbers.betweenStrict(pangolinPosX, blockPosX - pangolinWidth, blockPosX + blockWidth))
				if (Numbers.betweenStrict(pangolinPosY, blockPosY - pangolinHeight, blockPosY + blockHeight)) {
					if (entity.collides())
						return entity;
				}
		}
		return null;
	}
}
