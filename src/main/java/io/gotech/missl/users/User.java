package io.gotech.missl.users;

import io.gotech.missl.elections.VoteWeight;
import io.gotech.missl.elections.candidates.Candidate;

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
		return this.id == anotherUser.id;
	}
	
	public void vote(Candidate candidate) {
		candidate.receiveVote(id, voteWeight);
	}
}
