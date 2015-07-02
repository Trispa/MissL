package io.gotech.missl.domain.elections;

import io.gotech.missl.domain.elections.candidates.Candidate;
import io.gotech.missl.domain.elections.candidates.CandidateNumber;
import io.gotech.missl.domain.elections.candidates.CandidateNumberGenerator;
import io.gotech.missl.domain.elections.candidates.CandidatesRegistry;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.statistiques.CandidateStats;
import io.gotech.missl.statistiques.ElectionStats;

public class ElectionProcess {
    private ElectionRegistry electionRegistry;
    private CandidatesRegistry candidatesRegistry;
    private CandidateNumberGenerator candidateNumberGenerator;

    public ElectionProcess(ElectionRegistry electionRegistry,
	    CandidatesRegistry candidatesRegistry,
	    CandidateNumberGenerator candidateNumberGenerator) {
	this.electionRegistry = electionRegistry;
	this.candidatesRegistry = candidatesRegistry;
	this.candidateNumberGenerator = candidateNumberGenerator;
    }

    public void registerCandidate(Candidate candidate) {
	candidate.assignNumber(this.candidateNumberGenerator
		.getNextCandidateNumber());
	this.candidatesRegistry.add(candidate);
    }

    public void registerVote(UserId userID, CandidateNumber candidateNumber,
	    VoteWeight voteWeight) {
	if (this.candidatesRegistry
		.containsCandidateWithNumber(candidateNumber)) {
	    this.electionRegistry.registerVote(userID, candidateNumber,
		    voteWeight);
	    ;
	} else {
	    throw new NotRegisteredException(
		    "Candidate is not registered for this election");
	}
    }

    public CandidateStats getCandidateStats(CandidateNumber candidateNumber) {
	return electionRegistry.getCandidateStats(candidateNumber);

    }

    public ElectionStats getElectionStats() {
	return this.electionRegistry.getElectionStats();
    }
}
