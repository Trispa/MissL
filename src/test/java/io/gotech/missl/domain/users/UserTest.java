package io.gotech.missl.domain.users;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.gotech.missl.domain.elections.VoteWeight;
import io.gotech.missl.domain.elections.candidates.Candidate;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    @Mock
    Candidate candidate;
    private User user;

    private final UserId USER_ID = new UserId(UUID.randomUUID());
    private final UserGender GENDER = UserGender.FEMALE;
    private final VoteWeight USER_VOTE_WEIGHT = new VoteWeight(1);
    private final String FIRST_NAME = "John";
    private final String LAST_NAME = "Doe";
    private final UserAuthSource AUTH_SOURCE = new UserAuthSource(
	    UserAuthSource.Source.FACEBOOK, "1l5f5");

    @Before
    public void initialise() {
	user = new UserBuilder().withUserId(USER_ID).withSex(GENDER)
		.withVoteWeight(USER_VOTE_WEIGHT).withFirstName(FIRST_NAME)
		.withLastName(LAST_NAME).withAuthSource(AUTH_SOURCE).build();
    }

    @Test
    public void givenAUserAndCandidateWhenVoteItShouldTellCandidateToReceiveVoteFromUser()
	    throws Exception {
	user.vote(candidate);
	Mockito.verify(candidate).receiveVote(USER_ID, USER_VOTE_WEIGHT);
    }

    @Test
    public void givenUserIsFemaleWhenIsFemaleReturnTrue() {
	assertTrue(user.isFemale());
    }

    @Test
    public void givenUserIsNotFemaleWhenIsFemaleReturnFalse() {
	user = new UserBuilder().withSex(UserGender.MALE).withUserId(USER_ID)
		.withVoteWeight(USER_VOTE_WEIGHT).build();
	assertFalse(user.isFemale());
    }

    @Test
    public void givenAnOtherUserWhenEqualsReturnFalse() {

	UserId USERID = new UserId(UUID.randomUUID());
	User anotherUser = new UserBuilder().withSex(GENDER)
		.withVoteWeight(USER_VOTE_WEIGHT).withUserId(USERID).build();
	assertFalse(user.equals(anotherUser));
    }

    @Test
    public void givenTheSameUserWhenEqualsReturnFalse() {

	assertTrue(user.equals(user));
    }

    @Test
    public void givenAUserGetDTOShouldReturnExpectedDTO() {
	UserDTO userDTO = user.getDTO();
	verifyThatExpectedDTOReturned(userDTO);
    }

    private void verifyThatExpectedDTOReturned(UserDTO userDTO) {
	assertEquals(USER_ID, userDTO.id);
	assertEquals(GENDER, userDTO.gender);
	assertEquals(USER_VOTE_WEIGHT, userDTO.voteWeight);
	assertEquals(AUTH_SOURCE, userDTO.authSource);
	assertEquals(FIRST_NAME, userDTO.firstName);
	assertEquals(LAST_NAME, userDTO.lastName);
    }
}
