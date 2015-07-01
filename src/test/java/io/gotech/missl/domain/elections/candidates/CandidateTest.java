package io.gotech.missl.domain.elections.candidates;


import java.util.UUID;

import io.gotech.missl.domain.elections.Election;
import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.elections.candidates.Candidate;
import io.gotech.missl.domain.elections.candidates.CandidateMultipleNumberAssignmentException;
import io.gotech.missl.domain.elections.candidates.CandidateNumber;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserId;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CandidateTest {
	
	
	@Mock
	User user;
	@Mock
	Election election;
	
	CandidateNumber CANDIDATENUMBER = new CandidateNumber(3);
	private final UserId	VOTERID = new UserId(UUID.randomUUID());
	private final VoteWeight	VOTEWEIGHT = new VoteWeight(1);
	
	private Candidate  candidate;
	
	@Before
	public void initialise() {
		candidate = new Candidate(user, election);
		candidate.assignNumber(CANDIDATENUMBER);
	}
	
	@Test(expected=CandidateMultipleNumberAssignmentException.class)
	public void givenCandidateAlreadyAssignedNumberAssignNumberShouldRaiseAnException() {
		candidate.assignNumber(CANDIDATENUMBER);
	}


	@Test
	public void whenCandidateReceiveVoteItShouldHisElectionToRegisterTheVote() throws Exception
	{
		candidate.receiveVote(VOTERID, VOTEWEIGHT);
		Mockito.verify(election).registerVote(VOTERID, CANDIDATENUMBER, VOTEWEIGHT);
	}
	
}
