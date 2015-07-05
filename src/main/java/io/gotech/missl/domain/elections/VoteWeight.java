package io.gotech.missl.domain.elections;

public final class VoteWeight {
    public final Integer weight;

    public VoteWeight(int weight) {
	this.weight = weight;
    }

    public boolean equals(VoteWeight anotherVoteWeight) {
	return this.weight == anotherVoteWeight.weight;
    }

}
