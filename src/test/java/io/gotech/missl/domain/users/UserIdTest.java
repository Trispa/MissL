package io.gotech.missl.domain.users;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.gotech.missl.domain.users.UserId;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;


public class UserIdTest {

	
	private UserId userId;
	
	@Before
	public void initialise(){
	userId = new UserId(UUID.randomUUID());
	}
	
	public void givenAnOtherUserWhenEqualsReturnFalse() throws Exception {
		
		UserId	anOtherUserId = new UserId(UUID.randomUUID());
		
		assertFalse(userId.equals(anOtherUserId));
	}
	@Test
	public void givenTheSameUserWhenEqualsReturnFalse() throws Exception {
		
		assertTrue(userId.equals(userId));
	}

}
