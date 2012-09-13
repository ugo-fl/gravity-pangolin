package fr.gravity.pangolin.exception;

/**
 * This RuntimeException notifies that the map is invalid or has not been set.
 * @author Ugo Flamarion
 *
 */
public class InvalidMapException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2951255712532085397L;
	private String msg;
	
	public InvalidMapException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "InvalidMapException: " + msg;
	}
}
