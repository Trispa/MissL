package io.gotech.missl.elections.candidates;

import io.gotech.missl.elections.Election;
import io.gotech.missl.elections.VoteWeight;
import io.gotech.missl.users.User;
import io.gotech.missl.users.UserId;


public class Candidate
{
	private Election election;
	private User user;
	private CandidateNumber number;
	
	public Candidate(User user, Election election) {
		this.user = user;
		this.election = election;
		this.number = null;
	}
	
	public void assignNumber(CandidateNumber number) {
		if(this.number != null) {
			throw new CandidateMultipleNumberAssignmentException();
		}
		this.number = number;
	}
	
	public void receiveVote(UserId voterId, VoteWeight voteWeight) {
		election.registerVote(voterId, number, voteWeight);
	}
}
