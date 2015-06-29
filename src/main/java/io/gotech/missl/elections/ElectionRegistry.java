package io.gotech.missl.elections;

import io.gotech.missl.elections.candidates.CandidateNumber;
import io.gotech.missl.users.UserId;

public interface ElectionRegistry
{
	public void registerVote(UserId userId, CandidateNumber candidateNumber, VoteWeight voteWeight);
}
