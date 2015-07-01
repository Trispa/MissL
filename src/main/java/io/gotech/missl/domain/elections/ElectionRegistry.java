package io.gotech.missl.domain.elections;

import io.gotech.missl.domain.elections.candidates.CandidateNumber;
import io.gotech.missl.domain.users.UserId;

public interface ElectionRegistry
{
	public void registerVote(UserId userId, CandidateNumber candidateNumber, VoteWeight voteWeight);
}
