package io.gotech.missl.domain.elections;

public class VoteCount
{
	public int count;
	
	public VoteCount() {
		count = 0;
	}
	
	public VoteCount(int count) {
		this.count = count;
	}
	
	public boolean equals(VoteCount anotherVoteCount) {
		return this.count == anotherVoteCount.count;
	}
	
	public void add(VoteWeight voteWeight) {
		this.count += voteWeight.weight;
	}
}
