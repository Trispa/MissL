package io.gotech.missl.domain.elections.candidates;

import static org.junit.Assert.assertEquals;
import io.gotech.missl.domain.elections.Election;
import io.gotech.missl.domain.elections.VoteCount;
import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.statistiques.CandidateStats;

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

    private final CandidateNumber CANDIDATENUMBER = new CandidateNumber(3);
    private final UserId VOTERID = new UserId(new Long(4));
    private final VoteWeight VOTEWEIGHT = new VoteWeight(1);

    CandidateStats CANDIDATESTATS = new CandidateStats(new VoteCount(4), 2,
	    CANDIDATENUMBER);;
    private Candidate candidate;

    @Before
    public void initialise() {
	candidate = new Candidate(user, election);
	candidate.assignNumber(CANDIDATENUMBER);

    }

    @Test(expected = CandidateMultipleNumberAssignmentException.class)
    public void givenCandidateAlreadyAssignedNumberAssignNumberShouldRaiseAnException() {
	candidate.assignNumber(CANDIDATENUMBER);
    }

    @Test
    public void whenCandidateReceiveVoteItShouldHisElectionToRegisterTheVote()
	    throws Exception {
	candidate.receiveVote(VOTERID, VOTEWEIGHT);
	Mockito.verify(election).registerVote(VOTERID, CANDIDATENUMBER,
		VOTEWEIGHT);
    }

    @Test
    public void testGetCandidateStats() throws Exception {
	Mockito.when(election.getCandidateStats(CANDIDATENUMBER)).thenReturn(
		CANDIDATESTATS);
	CandidateStats candidateStats = candidate.getCandidateStats();
	Mockito.verify(election).getCandidateStats(CANDIDATENUMBER);
	assertEquals(CANDIDATESTATS, candidateStats);

    }

}
