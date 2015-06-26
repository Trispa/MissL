package io.gotech.missl.elections;

import static org.junit.Assert.*;
import io.gotech.missl.users.UserSex;

import org.junit.Before;
import org.junit.Test;



public class CandidateTest {
	
	
	
	private Candidate  candidate;
	
	@Before
	public void initialise(){
		
		this.candidate = new Candidate(UserSex.FEMALE);
	}
	

	@Test
	public void givenFemaleCandidateWhenIsFemalReturnTrue(){
		
		assertTrue(this.candidate.isFemale());
	}
	
	@Test
	public void givenMaleCandidateWhenIsFemalReturnFalse(){
		
		assertFalse(this.candidate.isFemale());
	}

}
