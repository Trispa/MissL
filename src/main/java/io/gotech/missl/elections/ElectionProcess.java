package io.gotech.missl.elections;

import io.gotech.missl.elections.candidates.Candidate;
import io.gotech.missl.elections.candidates.CandidateNumber;
import io.gotech.missl.elections.candidates.CandidateNumberGenerator;
import io.gotech.missl.elections.candidates.CandidatesRegistry;
import io.gotech.missl.users.UserId;

public class ElectionProcess
{
	private ElectionRegistry electionRegistry;
	private CandidatesRegistry candidatesRegistry;
	private CandidateNumberGenerator candidateNumberGenerator;
	
	public ElectionProcess(ElectionRegistry electionRegistry, CandidatesRegistry candidatesRegistry, CandidateNumberGenerator candidateNumberGenerator) {
		this.electionRegistry = electionRegistry;
		this.candidatesRegistry = candidatesRegistry;
		this.candidateNumberGenerator = candidateNumberGenerator;
	}
	
	public void registerCandidate(Candidate candidate) {
		candidate.assignNumber(this.candidateNumberGenerator.getNextCandidateNumber());
		this.candidatesRegistry.add(candidate);
	}
	
	public void registerVote(UserId userID, CandidateNumber candidateNumber, VoteWeight voteWeight) {
		if(this.candidatesRegistry.containsCandidateWithNumber(candidateNumber)) {
			this.electionRegistry.registerVote(userID, candidateNumber, voteWeight);;
		}
		else {
			throw new NotRegisteredException("Candidate is not registered for this election");
		}	
	}
}
