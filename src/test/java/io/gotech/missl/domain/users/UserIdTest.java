package io.gotech.missl.domain.users;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserIdTest {

    private UserId userId;

    @Before
    public void initialise() {
	userId = new UserId(new Long(4));
    }

    public void givenAnOtherUserWhenEqualsReturnFalse() throws Exception {

	UserId anOtherUserId = new UserId(new Long(4));

	assertFalse(userId.equals(anOtherUserId));
    }

    @Test
    public void givenTheSameUserWhenEqualsReturnFalse() throws Exception {

	assertTrue(userId.equals(userId));
    }

    @Test
    public void whenEqualsOnTwoUserIdNotSetItShouldReturnTrue() {
	assertTrue(UserId.NOT_SET.equals(UserId.NOT_SET));
    }

    @Test
    public void whenEqualsOnUserIdNotSetANdAnyOtherUserIdItShouldReturnFalse() {
	assertFalse(UserId.NOT_SET.equals(userId));
    }

}
