package io.gotech.missl.elections;

public class CandidateAlreadyInRegistryException extends RuntimeException
{
	
	private static final long serialVersionUID = 3166095365581593234L;

	public CandidateAlreadyInRegistryException(String message){
		super (message);
	}

}
