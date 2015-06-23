package io.gotech.missl.elections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class MissElectionTest
{

	private Candidate candidate;
	private  Voter voter;
	private ElectionRegistry electionRegistry;
	private CandidatesRegistry candidatesRegistry;
	private MissElection election;
	
	@Before
	public void initialise() {
		candidate = Mockito.mock(Candidate.class);
		voter = Mockito.mock(Voter.class);
		electionRegistry = Mockito.mock(ElectionRegistry.class);
		candidatesRegistry = Mockito.mock(CandidatesRegistry.class);
		election = new MissElection(electionRegistry, candidatesRegistry);
	}
	
	@Test(expected=NotRegisteredException.class)
	public void givenCandidateNotRegisteredForCurrentElectionWhenVoteShouldRaiseNotRegisteredException() throws Exception
	{
		Mockito.when(candidatesRegistry.isCandidateInRegistry(candidate)).thenReturn(false);
		election.vote(voter, candidate);
	}
	
	@Test
	public void givenCandidateRegisteredForCurrentElectionWhenVoterVoteShouldAddVoteTheElectionRegistry() {
		Mockito.when(candidatesRegistry.isCandidateInRegistry(candidate)).thenReturn(true);
		election.vote(voter, candidate);
		Mockito.verify(electionRegistry, Mockito.times(1)).registerVote(voter, candidate);
	}

}
