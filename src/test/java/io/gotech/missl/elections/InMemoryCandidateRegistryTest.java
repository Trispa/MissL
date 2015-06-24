package io.gotech.missl.elections;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class InMemoryCandidateRegistryTest {
	
	private Candidate candidate;
	private InMemoryCandidateRegistry inMemoryCandidateRegistry;
	private CandidatesRegistry candidatesRegistry;
	
	@Before
	public void initialise(){
		candidate  = Mockito.mock(Candidate.class);
		ArrayList<Candidate> listOfCandidate = new ArrayList<Candidate>();
		candidatesRegistry = Mockito.mock(CandidatesRegistry.class);
		inMemoryCandidateRegistry = new InMemoryCandidateRegistry(listOfCandidate);
	}

	@Test(expected = CandidateAlreadyInRegistryException.class)
	public void givenCandidateNotAddedInMomeryCandidateRegistryWhenAddShouldRaisAnCandidateAlreadyInRegistryException() throws Exception 
	{
		Mockito.when(candidatesRegistry.isCandidateInRegistry(candidate)).thenReturn(false);
		inMemoryCandidateRegistry.addCandidateInRegistry(candidate);
		
	}
	
}
