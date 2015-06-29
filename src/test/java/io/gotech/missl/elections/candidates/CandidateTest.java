package io.gotech.missl.elections.candidates;

import static org.junit.Assert.*;

import java.util.UUID;

import io.gotech.missl.elections.Election;
import io.gotech.missl.elections.VoteWeight;
import io.gotech.missl.elections.candidates.Candidate;
import io.gotech.missl.elections.candidates.CandidateMultipleNumberAssignmentException;
import io.gotech.missl.elections.candidates.CandidateNumber;
import io.gotech.missl.users.User;
import io.gotech.missl.users.UserId;
import io.gotech.missl.users.UserSex;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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
