package io.gotech.missl.domain.users;

import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.elections.candidates.Candidate;

public class User
{	
	private UserSex sex;
	private UserId id;
	private VoteWeight voteWeight;

	public User(UserId userId, UserSex userSex, VoteWeight weight){
		this.sex = userSex;	
		this.id = userId;
		this.voteWeight = weight;
	}
	
	public  boolean isFemale()
	{
		return this.sex.equals(UserSex.FEMALE);
	}
	
	public boolean equals(User anotherUser) {
		return this.id.equals(anotherUser.id);
	}
	
	public void vote(Candidate candidate) {
		candidate.receiveVote(id, voteWeight);
	}
}
