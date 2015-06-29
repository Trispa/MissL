package io.gotech.missl.elections;

public class VoteAlreadyRegisteredException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8463468517336948877L;
	
	public VoteAlreadyRegisteredException(String message) {
		super(message);
	}

}
