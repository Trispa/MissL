package io.gotech.missl.domain.elections;



import io.gotech.missl.domain.elections.candidates.Candidate;
import io.gotech.missl.domain.elections.candidates.CandidateNumber;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.statistiques.CandidateStats;
import io.gotech.missl.statistiques.ElectionStats;


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
	@Override
	public CandidateStats getCandidateStats(CandidateNumber candidateNumber){
		return this.electionProcess.getCandidateStats(candidateNumber);
	}

	@Override
	public ElectionStats getElectionStats(){
		return this.electionProcess.getElectionStats();
		
	}
	protected abstract void checkCandidateRequirements(User userToBecomeCandidate);
	

}
