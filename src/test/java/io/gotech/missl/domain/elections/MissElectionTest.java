package io.gotech.missl.domain.elections;

import static org.junit.Assert.assertEquals;
import io.gotech.missl.domain.elections.candidates.Candidate;
import io.gotech.missl.domain.elections.candidates.CandidateNumber;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.statistiques.CandidateStats;
import io.gotech.missl.statistiques.ElectionStats;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MissElectionTest {

    private static final UserId USER_ID = new UserId(new Long(7));
    private static final CandidateNumber CANDIDATE_NUMBER = new CandidateNumber(
	    4);
    private static final VoteWeight VOTE_WEIGHT = new VoteWeight(2);
    private User user;
    private User electionCreator;
    private ElectionProcess electionProcess;
    private MissElection missElection;
    private final CandidateNumber CANDIDATENUMBER = new CandidateNumber(3);
    private final DateTime START_DATE = new DateTime();
    private final DateTime END_DATE = START_DATE.plusDays(4);
    CandidateStats CANDIDATESTATS = new CandidateStats(new VoteCount(4), 2,
	    CANDIDATE_NUMBER);
    List<CandidateStats> listCandidateStatsTreedByRank = new ArrayList<CandidateStats>();
    ElectionStats ELECTIONSTATS = new ElectionStats(
	    listCandidateStatsTreedByRank);

    @Before
    public void initialise() {
	user = Mockito.mock(User.class);
	electionCreator = Mockito.mock(User.class);
	electionProcess = Mockito.mock(ElectionProcess.class);
	Mockito.when(user.isFemale()).thenReturn(true);

	missElection = new MissElection(electionCreator, electionProcess,
		START_DATE, END_DATE);

    }

    @Test
    public void givenCandidateRegisteredForCurrentElectionWhenVoterVoteShouldAddVoteTheElectionRegistry() {
	missElection.registerVote(USER_ID, CANDIDATE_NUMBER, VOTE_WEIGHT);
	Mockito.verify(electionProcess, Mockito.times(1)).registerVote(USER_ID,
		CANDIDATE_NUMBER, VOTE_WEIGHT);
    }

    @Test
    public void givenCandidateNotInRegistryCandidateWhenResgisterCandidateItShouldTellElectionProcessToAddCandidate() {

	missElection.registerCandidate(user);
	Mockito.verify(electionProcess, Mockito.times(1)).registerCandidate(
		Mockito.any(Candidate.class));
	;
    }

    @Test(expected = BadCandidateSexException.class)
    public void givenCandidateIsNotAFemaleWhenRegistrerCandidateInMissElectionShouldRaiseAnException() {
	Mockito.when(user.isFemale()).thenReturn(false);
	missElection.registerCandidate(user);
    }

    @Test
    public void testGetCandidateStats() throws Exception {
	Mockito.when(electionProcess.getCandidateStats(CANDIDATENUMBER))
		.thenReturn(CANDIDATESTATS);
	CandidateStats candidateStats = missElection
		.getCandidateStats(CANDIDATENUMBER);
	Mockito.verify(electionProcess).getCandidateStats(CANDIDATENUMBER);
	assertEquals(CANDIDATESTATS, candidateStats);

    }

    @Test
    public void testGetElectionStats() throws Exception {

	Mockito.when(electionProcess.getElectionStats()).thenReturn(
		ELECTIONSTATS);
	ElectionStats electionStats = missElection.getElectionStats();
	Mockito.verify(electionProcess).getElectionStats();
	assertEquals(ELECTIONSTATS, electionStats);
    }

}
