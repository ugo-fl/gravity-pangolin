package fr.gravity.pangolin;

import fr.gravity.pangolin.entity.pangolin.Pangolin.Direction;

public class Gravity {

//	public static enum Side { UP, DOWN, LEFT, RIGHT};

	private Direction direction;
	
	public Gravity(Direction direction) {
		this.direction = direction;
	}
	
	public static Direction getOppositeSide(Direction direction) {
		if (direction == Direction.UP)
			return Direction.DOWN;
		else if (direction == Direction.DOWN)
			return Direction.UP;
		else if (direction == Direction.LEFT)
			return Direction.RIGHT;
		else
			return Direction.LEFT;
	}
	
	/**
	 * Sets the gravity to the opposite of the current gravity side
	 */
	public void switchDirection() {
		direction = Gravity.getOppositeSide(direction);
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
}
