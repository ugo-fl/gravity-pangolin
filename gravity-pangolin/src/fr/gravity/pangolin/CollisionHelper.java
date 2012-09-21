package fr.gravity.pangolin;

import com.badlogic.gdx.utils.Array;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.util.Numbers;

public class CollisionHelper {

	/**
	 * 
	 * @param entity1
	 * @param entity2
	 * @return true if the left side of entity1 collides with entity2
	 */
	public static boolean collidesLeft(Entity entity1, Entity entity2) {
		if (entity1 == null || entity2 == null)
			return false;
		if (Numbers.between(entity1.getX(), entity2.getX(), entity2.getY() + entity2.getBoundingRectangle().getWidth()))
			return true;
		return false;
	}

	/**
	 * 
	 * @param entity1
	 * @param entity2
	 * @return true if the right side of entity1 collides with entity2
	 */
	public static boolean collidesRight(Entity entity1, Entity entity2) {
		if (entity1 == null || entity2 == null)
			return false;
		if (Numbers.between(entity1.getX() + entity1.getBoundingRectangle().getWidth(), entity2.getX(), entity2.getX()
				+ entity2.getBoundingRectangle().getWidth()))
			return true;
		return false;
	}

	/**
	 * 
	 * @param entity1
	 * @param entity2
	 * @return true if the up side of entity1 collides with entity2
	 */
	public static boolean collidesUp(Entity entity1, Entity entity2) {
		if (entity1 == null || entity2 == null)
			return false;
		if (Numbers.between(entity1.getY() + entity1.getBoundingRectangle().getHeight(), entity2.getY(), entity2.getY()
				+ entity2.getBoundingRectangle().getHeight()))
			return true;
		return false;
	}

	/**
	 * 
	 * @param entity1
	 * @param entity2
	 * @return true if the down side of entity1 collides with entity2
	 */
	public static boolean collidesDown(Entity entity1, Entity entity2) {
		if (entity1 == null || entity2 == null)
			return false;
		if (Numbers.between(entity1.getY(), entity2.getY(), entity2.getY() + entity2.getBoundingRectangle().getHeight()))
			return true;
		return false;
	}

	/**
	 * 
	 * @param entity
	 * @param entities
	 * @return the {@link Entity} that entity collides with. If there is none,
	 *         returns null.
	 */
	public static Entity collidesAny(Entity entity, Array<Entity> entities) {
		float entityPosX = entity.getX();
		float entityPosY = entity.getY();
		float entityWidth = entity.getBoundingRectangle().getWidth();
		float entityHeight = entity.getBoundingRectangle().getHeight();

		for (Entity any : entities) {
			float blockPosX = any.getBoundingRectangle().x;
			float blockPosY = any.getBoundingRectangle().y;
			float blockWidth = any.getBoundingRectangle().getWidth();
			float blockHeight = any.getBoundingRectangle().getHeight();

			if (Numbers.betweenStrict(entityPosX, blockPosX - entityWidth, blockPosX + blockWidth))
				if (Numbers.betweenStrict(entityPosY, blockPosY - entityHeight, blockPosY + blockHeight)) {
					return (Entity) any.hit(0, 0);
				}
		}
		return null;
	}
}
