package io.gotech.missl.domain.users;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6085075698894077656L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
