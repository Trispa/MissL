package io.gotech.missl.users;

import java.util.UUID;

import io.gotech.missl.elections.VoteWeight;
import io.gotech.missl.elections.candidates.Candidate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest
{
	@Mock
	Candidate candidate;
	private User user;
	private final UserId	USERID = new UserId(UUID.randomUUID());
	private final UserSex	USERSEX = UserSex.FEMALE;
	private final VoteWeight	USERVOTEWEIGHT = new VoteWeight(1);
	
	
	@Before
	public void initialise() {
		
		user = new User(USERID, USERSEX, USERVOTEWEIGHT);
	}
	@Test
	public void givenAUserAndCandidateWhenVoteItShouldTellCandidateToReceiveVoteFromUser() throws Exception
	{
		user.vote(candidate);
		Mockito.verify(candidate).receiveVote(USERID, USERVOTEWEIGHT);
	}

	
}
