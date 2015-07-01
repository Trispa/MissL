package io.gotech.missl.domain.users;

import static org.junit.Assert.*;

import java.util.UUID;

import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.elections.candidates.Candidate;
import io.gotech.missl.domain.users.User;
import io.gotech.missl.domain.users.UserId;
import io.gotech.missl.domain.users.UserSex;

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
	@Test
	public void givenUserIsFemaleWhenIsFemaleReturnTrue() throws Exception {
		assertTrue(user.isFemale());
	}
	@Test
	public void givenUserIsNotFemaleWhenIsFemaleReturnFalse() throws Exception {
		UserSex	USERSEX = UserSex.MALE;
		user = new User(USERID, USERSEX, USERVOTEWEIGHT);
		assertFalse(user.isFemale());
	}
	@Test
	public void givenAnOtherUserWhenEqualsReturnFalse() throws Exception {
		
		UserId	USERID = new UserId(UUID.randomUUID());
		User anOtherUser = new User(USERID, USERSEX, USERVOTEWEIGHT);
		assertFalse(user.equals(anOtherUser));
	}
	@Test
	public void givenTheSameUserWhenEqualsReturnFalse() throws Exception {
		
		assertTrue(user.equals(user));
	}
	
	
}
