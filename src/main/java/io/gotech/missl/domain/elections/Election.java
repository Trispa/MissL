package io.gotech.missl.domain.elections;

import io.gotech.missl.domain.elections.candidates.Candidate;
import io.gotech.missl.domain.elections.candidates.CandidateNumber;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserId;

public interface Election {
	
	public Candidate registerCandidate(User candidate);
	public void registerVote(UserId userID, CandidateNumber candidateNumber, VoteWeight voteWeight);
	
}