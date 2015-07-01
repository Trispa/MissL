package io.gotech.missl.domain.elections;

import io.gotech.missl.domain.elections.candidates.CandidateNumber;
import io.gotech.missl.domain.users.UserId;

public class Vote {
	
	final public VoteWeight voteWeight;
	final public CandidateNumber condidateNumber;
	final public UserId voterId;
	
	public Vote (CandidateNumber candidateNumber, UserId voterId, VoteWeight voteWeight)
	{
		this.voteWeight = voteWeight;
		this.voterId =  voterId;
		this.condidateNumber = candidateNumber;
	}

}
