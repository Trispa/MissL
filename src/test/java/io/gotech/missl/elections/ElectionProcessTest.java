package io.gotech.missl.elections;

import java.util.UUID;

import io.gotech.missl.elections.candidates.Candidate;
import io.gotech.missl.elections.candidates.CandidateNumber;
import io.gotech.missl.elections.candidates.CandidateNumberGenerator;
import io.gotech.missl.elections.candidates.CandidatesRegistry;
import io.gotech.missl.users.User;
import io.gotech.missl.users.UserId;

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
	@Mock
	private Candidate candidate;
	@Mock
	private User voter;
	
	private final CandidateNumber CANDIDATE_NUMBER = new CandidateNumber(4);
	private final UserId USER_ID = new UserId(UUID.randomUUID());
	private final VoteWeight VOTE_WEIGHT = new VoteWeight(2);
	
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
	
	

}
