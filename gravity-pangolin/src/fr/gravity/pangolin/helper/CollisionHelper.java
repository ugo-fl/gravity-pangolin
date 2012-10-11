package fr.gravity.pangolin.helper;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import fr.gravity.pangolin.entity.Entity;
import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;
import fr.gravity.pangolin.util.NumbersUtil;

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
		if (NumbersUtil.between(entity1.getX(), entity2.getX(), entity2.getX() + entity2.getBoundingRectangle().getWidth()))
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
		if (NumbersUtil.between(entity1.getX() + entity1.getBoundingRectangle().getWidth(), entity2.getX(), entity2.getX()
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
		if (NumbersUtil.between(entity1.getY() + entity1.getHeight(), entity2.getY(), entity2.getY() + entity2.getHeight()))
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
		if (NumbersUtil.between(entity1.getY(), entity2.getY(), entity2.getY() + entity2.getHeight()))
			return true;
		return false;
	}

	public static boolean collides(Entity entity1, Entity entity2, Direction direction) {
		if (direction == Direction.DOWN)
			return collidesDown(entity1, entity2);
		else if (direction == Direction.UP)
			return collidesUp(entity1, entity2);
		else if (direction == Direction.LEFT)
			return collidesLeft(entity1, entity2);
		else if (direction == Direction.RIGHT)
			return collidesRight(entity1, entity2);
		return false;
	}

	/**
	 * 
	 * @param entity
	 * @param entities
	 * @return the {@link Entity} that entity collides with. If there is none,
	 *         returns null.
	 */
//	public static Entity collidesAny(Entity entity, Array<Entity> entities) {
//		Rectangle entityBounds = entity.getBoundingRectangle();
//		float entityPosX = entityBounds.x;
//		float entityPosY = entityBounds.y;
//		float entityWidth = entityBounds.width;
//		float entityHeight = entityBounds.height;
//
//		Entity collidedEntity = null;
//		for (Entity any : entities) {
//			if (any == entity)
//				continue;
//
//			Rectangle blockBounds = any.getBoundingRectangle();
//			float blockPosX = blockBounds.getX();
//			float blockPosY = blockBounds.getY();
//			float blockWidth = blockBounds.getWidth();
//			float blockHeight = blockBounds.getHeight();
//
//			if (NumbersUtil.betweenStrict(entityPosX, blockPosX - entityWidth, blockPosX + blockWidth)) {
//				if (NumbersUtil.betweenStrict(entityPosY, blockPosY - entityHeight, blockPosY + blockHeight)) {
//					Entity returnedCollidedEntity = any.collides();
//					if (returnedCollidedEntity != null)
//						collidedEntity = returnedCollidedEntity;
//				}
//			}
//		}
//		return collidedEntity;
//	}
}
