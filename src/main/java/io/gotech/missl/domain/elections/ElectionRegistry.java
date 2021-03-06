package io.gotech.missl.domain.elections;

import io.gotech.missl.domain.elections.candidates.CandidateNumber;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.statistiques.CandidateStats;
import io.gotech.missl.statistiques.ElectionStats;

public interface ElectionRegistry
{
	public void registerVote(UserId userId, CandidateNumber candidateNumber, VoteWeight voteWeight);

	public CandidateStats getCandidateStats(CandidateNumber candidateNumber);

	public ElectionStats getElectionStats();
}
