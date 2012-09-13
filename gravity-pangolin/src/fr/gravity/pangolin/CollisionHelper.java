package fr.gravity.pangolin;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.pangolin.Pangolin;
import fr.gravity.pangolin.util.Numbers;

public class CollisionHelper {

	public static boolean collidesLeft(Entity entity1, Entity entity2) {
		if (entity1 == null || entity2 == null)
			return false;
		if (Numbers.between(entity1.getX(), entity2.getX(), entity2.getY()
				+ entity2.getBoundingRectangle().getWidth()))
			return true;
		return false;
	}

	public static boolean collidesRight(Entity entity1, Entity entity2) {
		if (entity1 == null || entity2 == null)
			return false;
		if (Numbers.between(entity1.getX() + entity1.getBoundingRectangle().getWidth(), entity2.getX(),
				entity2.getX() + entity2.getBoundingRectangle().getWidth()))
			return true;
		return false;
	}

	public static boolean collidesUp(Entity entity1, Entity entity2) {
		if (entity1 == null || entity2 == null)
			return false;
		if (Numbers.between(entity1.getY(), entity2.getY(), entity2.getY()
				+ entity2.getBoundingRectangle().getHeight()))
			return true;
		return false;
	}

	public static boolean collidesDown(Entity entity1, Entity entity2) {
		if (entity1 == null || entity2 == null)
			return false;
		if (Numbers.between(entity1.getY() + entity1.getBoundingRectangle().getHeight(), entity2.getY(),
				entity2.getY() + entity2.getBoundingRectangle().getHeight()))
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
	public static Entity collidesAny(Entity entity, Array<Entity> entities) {
		float entityPosX = entity.getX();
		float entityPosY = entity.getY();
		float entityWidth = entity.getBoundingRectangle().getWidth();
		float entityHeight = entity.getBoundingRectangle().getHeight();

		for (Entity any : entities) {
			float blockPosX = any.getX();
			float blockPosY = any.getY();
			float blockWidth = any.getBoundingRectangle().getWidth();
			float blockHeight = any.getBoundingRectangle().getHeight();

			if (Numbers.betweenStrict(entityPosX, blockPosX - entityWidth, blockPosX + blockWidth))
				if (Numbers.betweenStrict(entityPosY, blockPosY - entityHeight, blockPosY + blockHeight)) {
					if (any.collides())
						return any;
				}
		}
		return null;
	}
}
