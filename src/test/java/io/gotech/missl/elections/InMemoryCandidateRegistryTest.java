package io.gotech.missl.elections;

import static org.junit.Assert.*;
import io.gotech.missl.persistence.InMemoryCandidateRegistry;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryCandidateRegistryTest {
	
	private Candidate candidate;
	private Candidate candidateInRegistry;
	private InMemoryCandidateRegistry inMemoryCandidateRegistry;
	
	@Before
	public void initialise(){
		candidate  = Mockito.mock(Candidate.class);
		candidateInRegistry = Mockito.mock(Candidate.class);
		ArrayList<Candidate> listOfCandidate = new ArrayList<Candidate>();
		listOfCandidate.add(candidateInRegistry);
		
		inMemoryCandidateRegistry = new InMemoryCandidateRegistry(listOfCandidate);
	}
	
	@Test
	public void whenCandidateNotInRegistryContainsShouldReturnFalse() {
		assertFalse(inMemoryCandidateRegistry.contains(candidate));
	}
	
	@Test
	public void whenCandidateIsInRegistryContainsShouldReturnTrue() {
		assertTrue(inMemoryCandidateRegistry.contains(candidateInRegistry));
	}
	

	@Test(expected = CandidateAlreadyRegistredException.class)
	public void givenCandidateAddedInMomeryCandidateRegistryWhenAddShouldRaisAnCandidateAlreadyRegistredException() throws Exception 
	{
		inMemoryCandidateRegistry.add(candidateInRegistry);	
	}
	
	@Test
	public void givenCandidateNotInRegistryAddShouldPutCandidateInRegistry() {
		inMemoryCandidateRegistry.add(candidate);
		assertTrue(inMemoryCandidateRegistry.contains(candidate));
	}
	
	
	
}
