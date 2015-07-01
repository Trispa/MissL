package io.gotech.missl.elections;

import io.gotech.missl.elections.candidates.Candidate;
import io.gotech.missl.elections.candidates.CandidateNumber;
import io.gotech.missl.users.User;
import io.gotech.missl.users.UserId;

public abstract class GeneralElection implements Election
{
	protected ElectionProcess electionProcess;
	
	public GeneralElection(ElectionProcess electionProcess) {
		this.electionProcess = electionProcess;
	}
	@Override
	public Candidate registerCandidate(User userToBecomeCandidate)
	{
		checkCandidateRequirements(userToBecomeCandidate);
		Candidate candidate = new Candidate(userToBecomeCandidate, this);
		this.electionProcess.registerCandidate(candidate);
		return candidate;
		
		
	}

	@Override
	public void registerVote(UserId userID, CandidateNumber candidateNumber,
			VoteWeight voteWeight)
	{
		this.electionProcess.registerVote(userID, candidateNumber, voteWeight);
	}
	
	protected abstract void checkCandidateRequirements(User userToBecomeCandidate);

}
