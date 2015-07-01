package io.gotech.missl.elections.candidates;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CandidateNumberTest {

	private CandidateNumber candidateNumber;
	@Before
	public void initialise(){
	candidateNumber = new CandidateNumber(1);
	}
	@Test
	public void gevinTheSameCandidateNumberEqualsShouldReturnTrue() throws Exception {

		 CandidateNumber theSamecandidateNumber = new CandidateNumber(1);
		 assertTrue(candidateNumber.equals(theSamecandidateNumber));
	}
	@Test
	public void gevinTheAnOtherCandidateNumberEqualsShouldReturnFalse() throws Exception {

		 CandidateNumber anOthercandidateNumber = new CandidateNumber(4);
		 assertFalse(candidateNumber.equals(anOthercandidateNumber));
	}
	

}
