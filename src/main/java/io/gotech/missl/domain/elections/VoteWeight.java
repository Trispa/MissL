package io.gotech.missl.domain.elections;

public final class VoteWeight {
    public final Integer weight;
    public static final VoteWeight DEFAULT = new VoteWeight(2);

    public VoteWeight(int weight) {
	this.weight = weight;
    }

    public boolean equals(VoteWeight anotherVoteWeight) {
	return this.weight == anotherVoteWeight.weight;
    }

}
