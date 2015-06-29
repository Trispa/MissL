package io.gotech.missl.elections;

import io.gotech.missl.elections.candidates.Candidate;
import io.gotech.missl.elections.candidates.CandidateNumber;
import io.gotech.missl.users.User;
import io.gotech.missl.users.UserId;

public interface Election {
	
	public Candidate registerCandidate(User candidate);
	public void registerVote(UserId userId, CandidateNumber candidateNumber, VoteWeight voteweight);
	
}