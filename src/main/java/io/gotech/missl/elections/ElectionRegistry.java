package io.gotech.missl.elections;

public interface ElectionRegistry
{
	public boolean hasVoterVotedForCandidate(Voter voter, Candidate candidate);
	public void registerVote(Voter voter, Candidate candidate);
}
