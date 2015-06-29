package io.gotech.missl.elections;


import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class VoteCountTest
{
	public VoteCount initialCount = new VoteCount(2);
	public VoteWeight incrimentingWeight = new VoteWeight(3);
	public VoteCount resultingCount = new VoteCount(5);
	
	@Test
	public void givenAVoteCountAddingAVoteWeightShouldResultToTheExpectedVoteCount() throws Exception
	{
		initialCount.add(incrimentingWeight);
		assertTrue(initialCount.equals(resultingCount));
	}

}
