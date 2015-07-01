package io.gotech.missl.statistiques;

import io.gotech.missl.domain.elections.VoteCount;
import io.gotech.missl.domain.elections.candidates.CandidateNumber;

public class CandidateStats {
	
	final public VoteCount voteCount;
	final public int   rank;
	final public CandidateNumber candidateNumber;
	public CandidateStats(VoteCount voteCount, int  rank,  CandidateNumber candidateNumber){
		this.voteCount = voteCount;
		this.rank = rank;
		this.candidateNumber = candidateNumber;
	}

}
