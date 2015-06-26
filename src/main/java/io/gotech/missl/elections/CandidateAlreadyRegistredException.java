package io.gotech.missl.elections;

public class CandidateAlreadyRegistredException extends RuntimeException
{
	
	private static final long serialVersionUID = 3166095365581593234L;

	public CandidateAlreadyRegistredException(String message){
		super (message);
	}

}
