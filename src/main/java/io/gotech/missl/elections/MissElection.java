package io.gotech.missl.elections;

import io.gotech.missl.elections.candidates.Candidate;
import io.gotech.missl.elections.candidates.CandidateNumber;
import io.gotech.missl.users.User;
import io.gotech.missl.users.UserId;

public class MissElection implements Election
{
	private ElectionProcess electionProcess;
	
	public MissElection(ElectionProcess electionProcess) {
		this.electionProcess = electionProcess;
	}
	
	@Override
	public Candidate registerCandidate(User userToBecomeCandidate) {
		if(userToBecomeCandidate.isFemale()){
			Candidate candidate = new Candidate(userToBecomeCandidate, this);
			electionProcess.registerCandidate(candidate);
			return candidate;
		}
		else
			throw new BadCandidateSexException("Candidate is not a Female So can't registred in Miss Election");
	}
	
	@Override
	public void registerVote(UserId userID, CandidateNumber candidateNumber,
			VoteWeight voteWeight)
	{
		this.electionProcess.registerVote(userID, candidateNumber, voteWeight);
		
	}

	

}
