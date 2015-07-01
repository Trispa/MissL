package io.gotech.missl.domain.elections;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import io.gotech.missl.domain.elections.CandidateAlreadyRegistredException;
import io.gotech.missl.domain.elections.ElectionProcess;
import io.gotech.missl.domain.elections.ElectionRegistry;
import io.gotech.missl.domain.elections.NotRegisteredException;
import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.elections.candidates.Candidate;
import io.gotech.missl.domain.elections.candidates.CandidateNumber;
import io.gotech.missl.domain.elections.candidates.CandidateNumberGenerator;
import io.gotech.missl.domain.elections.candidates.CandidatesRegistry;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.statistiques.CandidateStats;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ElectionProcessTest
{
	
	@Mock
	private CandidatesRegistry	candidatesRegistry;
	@Mock
	private ElectionRegistry	electionRegistry;
	@Mock
	private CandidateNumberGenerator candidateNumberGenerator;
	@InjectMocks
	private ElectionProcess		electionProcess;
	//TODO la fonction a tester ne doit pas être mocker sinon coverage ne le consider pas comme un test
	//private ElectionProcess		electionProcess = new ElectionProcess(electionRegistry, candidatesRegistry,candidateNumberGenerator);
	
	@Mock
	private Candidate candidate;
	@Mock
	private User voter;
	
	private final CandidateNumber CANDIDATE_NUMBER = new CandidateNumber(4);
	private final UserId USER_ID = new UserId(UUID.randomUUID());
	private final VoteWeight VOTE_WEIGHT = new VoteWeight(2);
	private final CandidateNumber CANDIDATENUMBER = new CandidateNumber(3);
	private final CandidateStats CANDIDATESTATS = new CandidateStats(new VoteCount(4), 2); 
	@Test(expected=NotRegisteredException.class)
	public void givenCandidateNotRegisteredForCurrentElectionWhenVoteShouldRaiseNotRegisteredException() throws Exception
	{
		Mockito.when(candidatesRegistry.containsCandidateWithNumber(CANDIDATE_NUMBER)).thenReturn(false);
		electionProcess.registerVote(USER_ID, CANDIDATE_NUMBER, VOTE_WEIGHT);
	}
	
	@Test
	public void givenCandidateRegisteredForCurrentElectionWhenVoterVoteShouldAddVoteTheElectionRegistry() {
		Mockito.when(candidatesRegistry.containsCandidateWithNumber(CANDIDATE_NUMBER)).thenReturn(true);
		electionProcess.registerVote(USER_ID, CANDIDATE_NUMBER, VOTE_WEIGHT);
		Mockito.verify(electionRegistry, Mockito.times(1)).registerVote(USER_ID, CANDIDATE_NUMBER, VOTE_WEIGHT);
	}
	
	@Test
	public void givenCandidateNotInRegistryCandidateWhenRegisterCandidateShouldAddCandidateInRegistry(){
		electionProcess.registerCandidate(candidate);
		Mockito.verify(candidatesRegistry, Mockito.times(1)).add(candidate);
	}
	
	@Test
	public void whenRegisterCandidateItShouldAssignCandidateNumberFromCandidateNumberGenerator() {
		givenCandidateNumberGeneratedByCandidateNumberGenerator();
		electionProcess.registerCandidate(candidate);
		Mockito.verify(candidate).assignNumber(CANDIDATE_NUMBER);
	}
	
	private void givenCandidateNumberGeneratedByCandidateNumberGenerator()
	{
		Mockito.when(candidateNumberGenerator.getNextCandidateNumber()).thenReturn(CANDIDATE_NUMBER);
	}

	@Test(expected = CandidateAlreadyRegistredException.class)
	public void givenCandidateAlreadyRegistedWhenRegisterCandidateShouldRaiseAnException() throws Exception {
		Mockito.doThrow(CandidateAlreadyRegistredException.class).when(candidatesRegistry).add(candidate);
		electionProcess.registerCandidate(candidate);
		
	}

	@Test
	public void testGetCandidateStats() throws Exception {
		Mockito.when(electionRegistry.getCandidateStats(CANDIDATENUMBER)).thenReturn(CANDIDATESTATS);
		CandidateStats candidateStats = electionProcess.getCandidateStats(CANDIDATENUMBER);
		Mockito.verify(electionRegistry).getCandidateStats(CANDIDATENUMBER);
		assertEquals(CANDIDATESTATS, candidateStats); 
		
	}

	
	
	

}
