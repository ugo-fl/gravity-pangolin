package fr.gravity.pangolin;

public class Gravity {

	public static enum Side { UP, DOWN, LEFT, RIGHT};

	private Side side;
	
	public Gravity(Side side) {
		this.side = side;
	}
	
	public static Side getOppositeSide(Side side) {
		if (side == Side.UP)
			return Side.DOWN;
		else if (side == Side.DOWN)
			return Side.UP;
		else if (side == Side.LEFT)
			return Side.RIGHT;
		else
			return Side.LEFT;
	}
	
	/**
	 * Sets the gravity to the opposite of the current gravity side
	 */
	public void switchSide() {
		side = Gravity.getOppositeSide(side);
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}
	
}
