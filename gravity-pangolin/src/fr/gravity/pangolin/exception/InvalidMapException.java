package fr.gravity.pangolin.exception;

public class InvalidMapException extends Throwable {
	
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
