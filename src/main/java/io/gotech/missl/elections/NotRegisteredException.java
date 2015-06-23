package io.gotech.missl.elections;

public class NotRegisteredException extends RuntimeException
{

	private static final long	serialVersionUID	= 2795782232727308371L;
	
	public NotRegisteredException(String message) {
		super(message);
	}

}
